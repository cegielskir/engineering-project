package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.dbservice.model.ComparisonData;
import com.cgk.engineering.team.dbservice.repository.ReactiveArticleRepository;
import com.cgk.engineering.team.dbservice.repository.BasicComparisonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ReactiveArticleRepository articleRepository;

    @Autowired
    BasicComparisonRepository basicComparisonRepository;

    @GetMapping(value = "/{articleId}")
    public Mono<Article> getArticle(@PathVariable("articleId") String articleId){
        return articleRepository.findById(articleId);
    }

    @PostMapping
    public Mono<Article> addArticle(@RequestBody Article article){
        Mono<Article> articleAlreadyInDb = articleRepository.findByHash(article.getHash());
        if(articleAlreadyInDb.block() != null) {
            return articleAlreadyInDb;
        } else {
            return articleRepository.save(article);
        }
    }

    @GetMapping(value= "/stream/{articleId}/{articleIDSToCompare}/{metrics}", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ComparisonData> streamEvents(@PathVariable("articleId") String articleId,
                                             @PathVariable("articleIDSToCompare") List<String> articleIDS,
                                             @PathVariable("metrics") List<String> metrics) {

        Article article = articleRepository.findById(articleId).block();
        if(articleIDS != null && articleIDS.size() > 0) {
            System.out.println("wchodze 1");
            return articleRepository.findAllById(articleIDS)
                .map(a -> {
                    System.out.println("message1");
                    return createComparisonData(article, a, articleId, a.getId(), metrics);
                });
        } else {
            System.out.println("wchodze 2");
            return articleRepository.findAllByIdIsNot(articleId)
                .map(a -> {
                    System.out.println("message2");
                    return createComparisonData(article, a, articleId, a.getId(), metrics);
                });
        }
    }

    @GetMapping(value = "/find/content/{partOfContent}")
    public Flux<Article>  getArticleWithContent(@PathVariable("partOfContent") String partOfContent){
        return articleRepository.findByContentContains(partOfContent);
    }

    @GetMapping(value = "/find/title/{partOfContent}")
    public Flux<Article>  getArticleWithTitle(@PathVariable("partOfContent") String partOfTitle){
        return articleRepository.findByTitleContains(partOfTitle);
    }

    private ComparisonData createComparisonData(Article article1, Article article2, String id1, String id2, List<String> metrics){
        return new ComparisonData(article1, article2, getAlreadyInDbMap(id1, id2, metrics));
    }

    private Map<String, Boolean> getAlreadyInDbMap(String id1, String id2, List<String> metrics){
        System.out.println("get all");
        Map<String, Boolean> isAlreadyInDbMap = new HashMap<>();
        for(String metric : metrics){
            boolean isInDb = ((basicComparisonRepository.findByFirstArticleIDAndSecondArticleIDAndMetric(id1, id2, metric).block() != null) ||
                    (basicComparisonRepository.findByFirstArticleIDAndSecondArticleIDAndMetric(id2, id1, metric).block() != null));
            System.out.println(metric + ": " + isInDb);
            isAlreadyInDbMap.put(metric, isInDb);
        }

        return isAlreadyInDbMap;
    }
}

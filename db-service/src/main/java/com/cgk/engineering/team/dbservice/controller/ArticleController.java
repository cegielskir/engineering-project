package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.dbservice.model.BasicComparison;
import com.cgk.engineering.team.dbservice.model.ComparisonData;
import com.cgk.engineering.team.dbservice.repository.ReactiveArticleRepository;
import com.cgk.engineering.team.dbservice.repository.BasicComparisonRepository;
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
        articleIDS.remove(articleId);
        if(articleIDS != null && articleIDS.size() > 0) {
            return articleRepository.findAllById(articleIDS)
                .map(a -> createComparisonsData(article, a, metrics))
                .flatMap(Flux::fromIterable);
        } else {
            return articleRepository.findAllByIdNot(articleId)
                .map(a -> createComparisonsData(article, a, metrics))
                .flatMap(Flux::fromIterable);
        }
    }

    @GetMapping(value = "/find/content/{partOfContent}")
    public Flux<Article>  getArticleWithContent(@PathVariable("partOfContent") String partOfContent){
        return articleRepository.findByContentRegex(".*" + partOfContent + ".*");
    }

    @GetMapping(value = "/find/title/{partOfContent}")
    public Flux<Article>  getArticleWithTitle(@PathVariable("partOfContent") String partOfTitle){
        return articleRepository.findByTitleRegex(".*" + partOfTitle + ".*");
    }

    private List<ComparisonData> createComparisonsData(Article article1, Article article2, List<String> metrics){
        List<ComparisonData> comparisonDataList = new ArrayList<>();
        for(String metric : metrics) {
            BasicComparison basicComparison = basicComparisonRepository
                    .findFirstByArticleIDsIsAndMetricIs(new HashSet<>(Arrays.asList(article1.getId(), article2.getId())), metric).block();
            boolean isNotInDb = basicComparison == null;
            if(isNotInDb) {
                comparisonDataList.add(new ComparisonData(article1, article2, metric));
            }
        }
        return comparisonDataList;
    }
}

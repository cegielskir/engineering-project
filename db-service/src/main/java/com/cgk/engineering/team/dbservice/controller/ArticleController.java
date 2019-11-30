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
                .map(a -> createComparisonData(article, a, metrics));
        } else {
            return articleRepository.findAllByIdNot(articleId)
                .map(a -> createComparisonData(article, a, metrics));
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

    private ComparisonData createComparisonData(Article article1, Article article2, List<String> metrics){
        ComparisonData comparisonData = new ComparisonData(article1, article2);
        comparisonData.initComparisonMap(metrics);
        for(String metric : metrics) {
            BasicComparison basicComparison = basicComparisonRepository
                .findFirstByArticleIDsIsAndMetricIs(new HashSet<>(Arrays.asList(article1.getId(), article2.getId())), metric).block();
            boolean isInDb = basicComparison != null;
            if(isInDb) {
                comparisonData.addComparison(metric, basicComparison.getPercentage());
            }
        }
        return comparisonData;
    }
}

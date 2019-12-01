package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.dbservice.model.BasicComparison;
import com.cgk.engineering.team.dbservice.model.ComparisonData;
import com.cgk.engineering.team.dbservice.repository.ComparisonDataRepository;
import com.cgk.engineering.team.dbservice.repository.ReactiveArticleRepository;
import com.cgk.engineering.team.dbservice.repository.BasicComparisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ReactiveArticleRepository articleRepository;

    @Autowired
    BasicComparisonRepository basicComparisonRepository;

    @Autowired
    ComparisonDataRepository comparisonDataRepository;

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
    public ParallelFlux<ComparisonData> streamEvents(@PathVariable("articleId") String articleId,
                                                     @PathVariable("articleIDSToCompare") List<String> articleIDS,
                                                     @PathVariable("metrics") List<String> metrics) {
        Article theArticle = articleRepository.findById(articleId).block();
        articleIDS.remove(articleId);
        if(articleIDS != null && articleIDS.size() > 0) {
            return articleRepository.findAllByIdIn(new HashSet<>(articleIDS))
                    .map(article ->
                            comparisonDataRepository
                                    .findFirstByArticleIDsIn(new HashSet<>(Arrays.asList(articleId, article.getId())))
                                    .defaultIfEmpty(new ComparisonData(theArticle, article)))
                    .flatMap(Mono::flux)
                    .parallel(8)
                    .runOn(Schedulers.parallel());
        } else {
            return articleRepository.findAll()
                    .map(article ->
                            comparisonDataRepository
                                    .findFirstByArticleIDsIn(new HashSet<>(Arrays.asList(articleId, article.getId())))
                                    .defaultIfEmpty(new ComparisonData(theArticle, article)))
                    .flatMap(Mono::flux)
                    .parallel(8)
                    .runOn(Schedulers.parallel());
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
}

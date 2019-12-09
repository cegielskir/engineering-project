package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.dbservice.model.ComparisonData;
import com.cgk.engineering.team.dbservice.repository.ComparisonDataRepository;
import com.cgk.engineering.team.dbservice.repository.ReactiveArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ReactiveArticleRepository articleRepository;

    @Autowired
    ComparisonDataRepository basicComparisonRepository;

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

    @GetMapping(value= "/stream/{articleId}/{articleIDSToCompare}", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public ParallelFlux<ComparisonData> streamEvents(@PathVariable("articleId") String articleId,
                                                     @PathVariable("articleIDSToCompare") List<String> articleIDS) {
        articleIDS.remove(articleId);
        if(articleIDS != null && articleIDS.size() > 0) {
            return getComparisonDataFlux(articleRepository.findAllByIdIn(new HashSet<>(articleIDS)), articleId);
        } else {
            return getComparisonDataFlux(articleRepository.findAll(), articleId);
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

    private ParallelFlux<ComparisonData> getComparisonDataFlux(Flux<Article> articleFlux, String articleId){
        Article theArticle = articleRepository.findById(articleId).block();
        return articleFlux
                .filter(article -> !article.getId().equals(articleId))
                .map(article ->
                    comparisonDataRepository
                        .findFirstByArticleIDsIn(new HashSet<>(Arrays.asList(articleId, article.getId())))
                        .defaultIfEmpty(new ComparisonData(theArticle, article)))
                .flatMap(Mono::flux)
                .parallel(8)
                .runOn(Schedulers.parallel());
    }
}

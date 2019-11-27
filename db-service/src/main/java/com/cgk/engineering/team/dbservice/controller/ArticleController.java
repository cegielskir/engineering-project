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

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value= "/stream/{articleId}/{articleIDSToCompare}", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ComparisonData> streamEvents(@PathVariable("articleId") String articleId,
                                             @PathVariable("articleIDSToCompare") List<String> articleIDS) {
        Article article = articleRepository.findById(articleId).block();
        articleIDS.remove(articleId);
        if(articleIDS != null && articleIDS.size() > 0) {
            return articleRepository.findAllById(articleIDS)
                .map(a ->
                    new ComparisonData(article, a)
            );
        } else {
            return articleRepository.findAll()
                .map(a ->
                        new ComparisonData(article, a)
                );
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
}

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
    public Mono<Article> getArticle(@PathVariable("articleId") ObjectId articleId){
        return articleRepository.findById(articleId.toString());
    }

    @PostMapping
    public Mono<Article> addArticle(@RequestBody Article article){
        Mono<Article> articleAlreadyInDb = articleRepository.findByHash(article.getHash());
        if(articleAlreadyInDb.block() != null) {
            System.out.println("The same");
            return articleAlreadyInDb;
        }
        else{
            System.out.println("Not The same");
            return articleRepository.save(article);
        }
    }

    @GetMapping(value= "/stream/{articleId}", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ComparisonData> streamEvents(@PathVariable("articleId") ObjectId articleId) {

        Article article = articleRepository.findById(articleId.toString()).block();

        return articleRepository.findAll()
            .map(a ->
                 new ComparisonData(article, a)
            );
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

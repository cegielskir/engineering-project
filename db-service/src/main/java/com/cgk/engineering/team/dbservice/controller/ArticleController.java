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
        return articleRepository.save(article);
    }

    @GetMapping(value = "/find/{phrase}")
    public List<Article> getArticles(@PathVariable("phrase") String phrase){
        return articleRepository.findArticlesByContentContains(phrase);
    }

    @GetMapping(value= "/stream/{articleId}", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ComparisonData> streamEvents(@PathVariable("articleId") ObjectId articleId) {

        Article article = articleRepository.findById(articleId.toString()).block();
        System.out.println("Article " + article.toString());
        System.out.println("Got article"
        );
        return articleRepository.findAll()
                .map(a ->
                     new ComparisonData(
                                    article,
                                    a,
                                    null)

                );
    }
    //basicComparisonRepository.findByFirstArticleIDAndSecondArticleID(articleId.toString(), new ObjectId(a.get_id()).toString()).block()
}

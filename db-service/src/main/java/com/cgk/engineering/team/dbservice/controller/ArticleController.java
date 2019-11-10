package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.dbservice.model.ComparisonData;
import com.cgk.engineering.team.dbservice.repository.ArticleRepository;
import com.cgk.engineering.team.dbservice.repository.BasicComparisonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    BasicComparisonRepository basicComparisonRepository;

    @GetMapping(value = "/{articleId}")
    public Article getArticle(@PathVariable("articleId") ObjectId articleId){
        return articleRepository.findBy_id(articleId);
    }

    @GetMapping
    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    @PostMapping
    public Article addArticle(@RequestBody Article article){
        articleRepository.save(article);
        return article;
    }

    @GetMapping(value= "/stream/{articleId}", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ComparisonData> streamEvents(@PathVariable("articleId") ObjectId articleId) {

        return Flux.fromStream(articleRepository.getAllStream())
                .map(a -> {
                    return new ComparisonData(
                                    articleRepository.findBy_id(articleId),
                                    a,
                                    basicComparisonRepository.findByFirstArticleIDAndSecondArticleID(articleId, new ObjectId(a.get_id()))
                            );
                        }
                );
    }


}

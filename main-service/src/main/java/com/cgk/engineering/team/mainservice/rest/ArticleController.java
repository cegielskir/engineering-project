package com.cgk.engineering.team.mainservice.rest;

import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    DatabaseServiceClient dbClient;

    @GetMapping(value = "/{articleId}")
    public Article getArticle(@PathVariable("articleId") ObjectId articleId){
        return dbClient.getArticle(articleId);
    }

    @GetMapping
    public List<Article> getArticles(){
        return dbClient.getArticles();
    }

    @PostMapping
    public Article addArticle(@RequestBody Article article){
        return dbClient.addArticle(article);
    }

    @GetMapping(value = "/find/content/{partOfContent}")
    public Article getArticlesWithConent(@PathVariable("partOfContent") String partOfConent){
        return dbClient.getArticlesWithContent(partOfConent);
    }
}

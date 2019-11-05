package com.cgk.engineering.team.mainservice.rest;

import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.Article;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        dbClient.addArticle(article);
        return article;
    }


}
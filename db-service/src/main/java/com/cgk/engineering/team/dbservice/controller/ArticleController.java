package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.dbservice.repository.ArticleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

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


}

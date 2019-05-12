package com.cgk.engineering.team.mainservice.controller;


import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.repository.ArticleRepository;
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
        System.out.println(articleRepository.findAll());
        System.out.println(articleId);
        return articleRepository.findBy_id(articleId);
    }

    @GetMapping
    public List<Article> getArticles(){
        System.out.println(articleRepository.findAll());
        System.out.println();
        return articleRepository.findAll();
    }

    @PostMapping
    public Article addArticle(@RequestBody Article article){
        System.out.println(article);
        articleRepository.save(article);
        return article;
    }


}

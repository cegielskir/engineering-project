package com.cgk.engineering.team.coreservice.controller;


import com.cgk.engineering.team.coreservice.model.Article;
import com.cgk.engineering.team.coreservice.repository.ArticleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/core/articles/{articleId}")
    public Article getArticle(@PathVariable ObjectId articleId){
        System.out.println(articleRepository.findAll());
        System.out.println(articleId);
        return articleRepository.findBy_id(articleId);
    }

    @PostMapping("/core/articles")
    public Article addArticle(@RequestBody Article article){
        System.out.println(article);
        articleRepository.save(article);
        return article;
    }


}

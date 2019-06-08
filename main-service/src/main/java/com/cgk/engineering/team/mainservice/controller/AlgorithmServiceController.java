package com.cgk.engineering.team.mainservice.controller;


import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.mainservice.repository.ArticleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/compare")
@EnableFeignClients
public class AlgorithmServiceController {


    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AlgorithmClient algorithmClient;

    @GetMapping(value = "/{articleId}")
    public List<Comparison> getComparison(@PathVariable("articleId") ObjectId articleId){
        List<Article> articles = articleRepository.findAll();
        Article article = articleRepository.findBy_id(articleId);
        articles.remove(article);
        List<Comparison> comparisons = new ArrayList<>();
        for(Article theArticle : articles){
            comparisons.add(algorithmClient.getComparison(articleId, new ObjectId(theArticle.get_id())));
        }
        return comparisons;
    }
}

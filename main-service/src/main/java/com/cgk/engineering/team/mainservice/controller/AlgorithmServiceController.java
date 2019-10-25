package com.cgk.engineering.team.mainservice.controller;

import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/compare")
@EnableFeignClients
public class AlgorithmServiceController {


    @Autowired
    private DatabaseServiceClient dbClient;

    @Autowired
    private AlgorithmClient algorithmClient;

    @GetMapping(value = "/{articleId}")
    public List<Comparison> getComparison(@PathVariable("articleId") ObjectId articleId){
        List<Article> articles = dbClient.getArticles();
        Article article = dbClient.getArticle(articleId);
        articles.remove(article);
        List<Comparison> comparisons = new ArrayList<>();
        for(Article theArticle : articles){
            comparisons.add(algorithmClient.getComparison(new ComparisonData(article, theArticle)));
        }
        return comparisons;
    }
}

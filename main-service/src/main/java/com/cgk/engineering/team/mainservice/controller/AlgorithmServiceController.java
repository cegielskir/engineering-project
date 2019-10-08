package com.cgk.engineering.team.mainservice.controller;


import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Comparison> getComparison(@PathVariable("articleId") ObjectId articleId, @RequestParam("metric") String metric){
        List<Article> articles = dbClient.getArticles();
        Article article = dbClient.getArticle(articleId);
        if(article != null) {
            articles.remove(article);
            List<Comparison> comparisons = new ArrayList<>();
            for (Article theArticle : articles) {
                ComparisonData cd = new ComparisonData(article,theArticle);
                cd.setMetric(metric);
                comparisons.add(algorithmClient.getComparisonWithChosenMetric(cd));
            }
            return comparisons;

        }
        return null;
    }

    @GetMapping(value="/two")
    public Comparison getComparison(@RequestParam("articleId1") ObjectId articleId1,
                                          @RequestParam("articleId2") ObjectId articleId2){
        Article article1 = dbClient.getArticle(articleId1);
        Article article2 = dbClient.getArticle(articleId2);
        if(article1 != null && article2 != null) {
            return algorithmClient.getComparison(new ComparisonData(article1, article2));
        }
        return null;
    }
}

package com.cgk.engineering.team.mainservice.rest;

import com.cgk.engineering.team.mainservice.client.comparison.ComparisonServiceController;
import com.cgk.engineering.team.mainservice.client.comparison.services.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.DetailsComparison;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compare")
@EnableFeignClients
public class AlgorithmServiceController {


    @Autowired
    private DatabaseServiceClient dbClient;

    @Autowired
    private ComparisonServiceController comparisonServiceController;

    @GetMapping(value = "/{articleId}")
    public List<Comparison> getComparison(@PathVariable("articleId") ObjectId articleId,
                                          @RequestParam(value = "threshold", required = false) int threshold,
                                          @RequestParam("metric") String metric){
        List<Article> articles = dbClient.getArticles().stream().filter(a -> !new ObjectId(a.get_id()).equals(articleId)).collect(Collectors.toList());
        Article article = dbClient.getArticle(articleId);
        if(article != null) {
            List<Comparison> comparisons = new ArrayList<>();
            for (Article theArticle : articles) {
                ComparisonData comparisonData = new ComparisonData(article, theArticle);
                comparisonData.setMetric(metric);
                Comparison comparison = comparisonServiceController.getBasicComparison(comparisonData);
                if(comparison.getPercentage() > threshold) {
                    comparisons.add(comparison);
                }
            }

            return comparisons;
        }
        return null;
    }

    @GetMapping(value="/two")
    public DetailsComparison getComparison(@RequestParam("articleId1") ObjectId articleId1,
                                           @RequestParam("articleId2") ObjectId articleId2,
                                           @RequestParam("metric") String metric){

        Article article1 = dbClient.getArticle(articleId1);
        Article article2 = dbClient.getArticle(articleId2);
        ComparisonData comparisonData = new ComparisonData(article1, article2);
        comparisonData.setMetric(metric);
        if(article1 != null && article2 != null) {
            return comparisonServiceController.getDetailedComparison(comparisonData);
        }

        return null;
    }
}

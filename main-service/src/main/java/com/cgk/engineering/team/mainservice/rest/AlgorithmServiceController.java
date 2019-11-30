package com.cgk.engineering.team.mainservice.rest;

import com.cgk.engineering.team.mainservice.client.comparison.ComparisonServiceController;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.ComparisonRequest;
import com.cgk.engineering.team.mainservice.model.DetailedComparison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compare")
@EnableFeignClients
public class AlgorithmServiceController {


    @Autowired
    private DatabaseServiceClient dbClient;

    @Autowired
    private ComparisonServiceController comparisonServiceController;

    @GetMapping(value="/two")
    public DetailedComparison getComparison(@RequestParam("articleId1") String articleId1,
                                            @RequestParam("articleId2") String articleId2,
                                            @RequestParam("metric") String metric){

        Article article1 = dbClient.getArticle(articleId1);
        Article article2 = dbClient.getArticle(articleId2);
        ComparisonRequest comparisonRequest = new ComparisonRequest(article1, article2, metric);
        comparisonRequest.setMetric(metric);
        if(article1 != null && article2 != null) {
            return comparisonServiceController.getDetailedComparison(comparisonRequest);
        }

        return null;
    }
}

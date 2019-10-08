package com.cgk.engineering.team.simpleclient.controller;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.simpleclient.model.Comparison;
import com.cgk.engineering.team.simpleclient.model.LCPComparator;
import com.cgk.engineering.team.simpleclient.model.SimmetricsComparator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/algorithm")
public class AlgorithmController {

    @PostMapping
    public Comparison getComparison(@RequestBody ComparisonData comparisonData){


        //LevenshteinComparator levenshteinComparator = new LevenshteinComparator(article1, article2);

        //return levenshteinComparator.compareArticles();
        if(comparisonData.getMetric().equals("")){
            LCPComparator lcpc = new LCPComparator(comparisonData.getArticle1(), comparisonData.getArticle2());
            return lcpc.compareArticles();
        }
        else{
            SimmetricsComparator simmetricsComparator = new SimmetricsComparator(comparisonData.getArticle1(), comparisonData.getArticle2(), comparisonData.getMetric());
            return simmetricsComparator.compareArticles();
        }
    }


}

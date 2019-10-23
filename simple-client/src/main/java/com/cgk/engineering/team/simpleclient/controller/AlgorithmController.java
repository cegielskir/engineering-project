package com.cgk.engineering.team.simpleclient.controller;

import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.simpleclient.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/algorithm")
public class AlgorithmController {

    @PostMapping
    public IComparison getComparison(@RequestBody ComparisonData comparisonData) {


        //LevenshteinComparator levenshteinComparator = new LevenshteinComparator(article1, article2);

        //return levenshteinComparator.compareArticles();
        if (comparisonData.getMetric().equals("")) {
            LCSComparator lcsc = new LCSComparator(comparisonData.getArticle1(), comparisonData.getArticle2());
            return lcsc.compareArticles();
        } else {
            SimmetricsComparator simmetricsComparator = new SimmetricsComparator(comparisonData.getArticle1(), comparisonData.getArticle2(), comparisonData.getMetric());
            return simmetricsComparator.compareArticles();
        }
    }
}

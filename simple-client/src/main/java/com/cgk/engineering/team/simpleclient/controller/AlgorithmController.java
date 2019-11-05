package com.cgk.engineering.team.simpleclient.controller;

import com.cgk.engineering.team.simpleclient.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/algorithm")
public class AlgorithmController {

//    @PostMapping
//    public Comparison getComparison(@RequestBody ComparisonData comparisonData) {
//        //LevenshteinComparator levenshteinComparator = new LevenshteinComparator(article1, article2);
//
//        //return levenshteinComparator.compareArticles();
//        if (comparisonData.getMetric().equals("")) {
//            LCSComparator lcsc = new LCSComparator(comparisonData.getArticle1(), comparisonData.getArticle2());
//            return lcsc.compareArticles();
//        } else {
//            SimmetricsComparator simmetricsComparator = new SimmetricsComparator(comparisonData.getArticle1(), comparisonData.getArticle2(), comparisonData.getMetric());
//            return simmetricsComparator.compareArticles();
//        }
//    }

    @PostMapping
    public DetailsComparison getComparison(@RequestBody ComparisonData comparisonData) {
        LCSComparator lcsc = new LCSComparator(comparisonData.getArticle1(), comparisonData.getArticle2());
        return lcsc.compareArticles();
    }

    @PostMapping
    public BasicComparison getComparisonWithChosenMetric(@RequestBody ComparisonData comparisonData) {
        SimmetricsComparator simmetricsComparator =
                new SimmetricsComparator(comparisonData.getArticle1(),
                        comparisonData.getArticle2(),
                        comparisonData.getMetric());
        return simmetricsComparator.compareArticles();

    }

}

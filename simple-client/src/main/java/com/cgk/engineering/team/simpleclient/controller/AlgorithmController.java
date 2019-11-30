package com.cgk.engineering.team.simpleclient.controller;

import com.cgk.engineering.team.simpleclient.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/algorithm")
public class AlgorithmController {

    @PostMapping("/detailed")
    public DetailedComparison getComparison(@RequestBody ComparisonData comparisonData) {
        LCSComparator lcsc = new LCSComparator(comparisonData.getArticle1(), comparisonData.getArticle2());
        return lcsc.compareArticles();
    }

    @PostMapping("/basic")
    public BasicComparison getComparisonWithChosenMetric(@RequestBody ComparisonData comparisonData) {
        SimmetricsComparator simmetricsComparator =
                new SimmetricsComparator(comparisonData.getArticle1(),
                        comparisonData.getArticle2(),
                        comparisonData.getMetric());
        return simmetricsComparator.compareArticles();

    }

}

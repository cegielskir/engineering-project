package com.cgk.engineering.team.comparisonservice.controller;

import com.cgk.engineering.team.comparisonservice.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/algorithm")
public class AlgorithmController {

    @PostMapping("/detailed")
    public DetailedComparison getComparison(@RequestBody ComparisonRequest comparisonRequest) {
        LCSComparator lcsc = new LCSComparator(comparisonRequest.getArticle1(), comparisonRequest.getArticle2());
        return lcsc.compareArticles();
    }

    @PostMapping("/basic")
    public BasicComparison getComparisonWithChosenMetric(@RequestBody ComparisonRequest comparisonRequest) {
        SimmetricsComparator simmetricsComparator =
                new SimmetricsComparator(comparisonRequest.getArticle1(),
                        comparisonRequest.getArticle2(),
                        comparisonRequest.getMetric());
        return simmetricsComparator.compareArticles();

    }

}

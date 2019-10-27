package com.cgk.engineering.team.simpleclient.controller;

import com.cgk.engineering.team.simpleclient.model.Comparison;
import com.cgk.engineering.team.simpleclient.model.ComparisonData;
import com.cgk.engineering.team.simpleclient.model.FastComparison;
import com.cgk.engineering.team.simpleclient.model.LCPComparator;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/algorithm")
public class AlgorithmController {

    @PostMapping(value = "/accurate")
    public Comparison getAccurateComparison(@RequestBody ComparisonData comparisonData){

        //LevenshteinComparator levenshteinComparator = new LevenshteinComparator(article1, article2);

        //return levenshteinComparator.compareArticles();
        LCPComparator lcpc = new LCPComparator(comparisonData.getArticle1(), comparisonData.getArticle2());
        return lcpc.compareArticles();
    }


    @PostMapping(value = "/fast")
    public FastComparison getFastComparison(@RequestBody ComparisonData comparisonData){
//      LevenshteinComparator levenshteinComparator = new LevenshteinComparator(article1, article2);
//      return levenshteinComparator.compareArticles();
        LCPComparator lcpc = new LCPComparator(comparisonData.getArticle1(), comparisonData.getArticle2());
        return lcpc.fastCompareArticles();
    }


}

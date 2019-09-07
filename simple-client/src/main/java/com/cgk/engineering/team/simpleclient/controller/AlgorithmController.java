package com.cgk.engineering.team.simpleclient.controller;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.simpleclient.client.MainServiceClient;
import com.cgk.engineering.team.simpleclient.model.LCPComparator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/algorithm")
public class AlgorithmController {

    @GetMapping
    public Comparison getComparison(@RequestBody Article article1, @RequestBody Article article2){

        //LevenshteinComparator levenshteinComparator = new LevenshteinComparator(article1, article2);

        //return levenshteinComparator.compareArticles();
        LCPComparator lcpc = new LCPComparator(article1, article2);
        return lcpc.compareArticles();
    }


}

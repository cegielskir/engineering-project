package com.cgk.engineering.team.simpleclient.controller;

import com.cgk.engineering.team.simpleclient.client.CoreClient;
import com.cgk.engineering.team.simpleclient.model.Article;
import com.cgk.engineering.team.simpleclient.model.Comparison;
import com.cgk.engineering.team.simpleclient.model.LCPComparator;
import com.cgk.engineering.team.simpleclient.model.LevenshteinComparator;
import com.cgk.engineering.team.simpleclient.repository.ArticleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/algorithm")
public class AlgorithmController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CoreClient coreClient;

    @GetMapping
    public Comparison getComparison(@RequestParam(name = "id_1") ObjectId id1, @RequestParam(name = "id_2") ObjectId id2){
        Article article1 = articleRepository.findBy_id(id1);
        if(article1 == null){
            System.out.println("No article with id = " + id1 + " in the database");
            article1 = coreClient.getArticle(id1);
            articleRepository.save(article1);
        }

        Article article2 = articleRepository.findBy_id(id2);
        if(article2 == null){
            System.out.println("No article with id = " + id2 + " in the database");
            article2 = coreClient.getArticle(id2);
            articleRepository.save(article2);
        }

        //LevenshteinComparator levenshteinComparator = new LevenshteinComparator(article1, article2);

        //return levenshteinComparator.compareArticles();
        LCPComparator lcpc = new LCPComparator(article1, article2);
        return lcpc.compareArticles();
    }


}

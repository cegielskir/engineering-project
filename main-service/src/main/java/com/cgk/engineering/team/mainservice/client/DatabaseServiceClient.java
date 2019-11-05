package com.cgk.engineering.team.mainservice.client;

import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.BasicComparison;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "db-service")
public interface DatabaseServiceClient {

    @GetMapping("/article/{articleId}")
    Article getArticle(@PathVariable("articleId") ObjectId id);

    @GetMapping("/article")
    List<Article> getArticles();

    @PostMapping("/article")
    Article addArticle(@RequestBody Article article);

    @PostMapping("/basic-comparison")
    BasicComparison addComparison(@RequestBody BasicComparison basicComparison);
}

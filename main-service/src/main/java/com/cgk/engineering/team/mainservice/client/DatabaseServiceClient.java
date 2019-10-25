package com.cgk.engineering.team.mainservice.client;

import com.cgk.engineering.team.mainservice.model.Article;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "db-service")
public interface DatabaseServiceClient {

    @GetMapping("/article")
    Article getArticle(@RequestParam("id") ObjectId id);

    @GetMapping("/article")
    List<Article> getArticles();

    @PostMapping("/article")
    Article addArticle(@RequestBody Article article);
}

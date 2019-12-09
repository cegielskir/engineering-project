package com.cgk.engineering.team.mainservice.client;

import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.BasicComparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.ComparisonRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "db-service")
public interface DatabaseServiceClient {

    @GetMapping("/article/{articleId}")
    Article getArticle(@PathVariable("articleId") String id);

    @GetMapping("/article")
    List<Article> getArticles();

    @PostMapping("/article")
    Article addArticle(@RequestBody Article article);

    @GetMapping("/article/find/content/{partOfContent}")
    Article getArticlesWithContent(@PathVariable("partOfContent") String partOfContent);

    @GetMapping("/article/find/title/{partOfTitle}")
    Article getArticlesWithTitle(@PathVariable("partOfTitle") String partOfTitle);

    @PostMapping("/comparison-data")
    ComparisonData addComparisonData(@RequestBody ComparisonData comparisonData);
}

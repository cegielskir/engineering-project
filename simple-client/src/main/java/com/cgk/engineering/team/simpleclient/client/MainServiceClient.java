package com.cgk.engineering.team.simpleclient.client;

import com.cgk.engineering.team.dbservice.model.Article;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "main-service")
public interface MainServiceClient {

    @GetMapping("/article/{articleId}")
    Article getArticle(@PathVariable("articleId") ObjectId articleId);
}

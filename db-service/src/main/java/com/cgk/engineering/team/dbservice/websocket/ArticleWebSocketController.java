package com.cgk.engineering.team.dbservice.websocket;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.dbservice.model.ComparisonData;
import com.cgk.engineering.team.dbservice.repository.ArticleRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleWebSocketController {

    private SimpMessagingTemplate template;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    public ArticleWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/article/{articleID}")
    public void getArticles(@DestinationVariable ObjectId articleID) throws Exception {
        Article article = articleRepository.findBy_id(articleID);
        articleRepository.getAllStream()
                .filter(art -> !art.get_id().equals(articleID.toString()))
                .forEach(articleToCompare -> sendArticle(article, articleToCompare));
    }


    public void sendArticle(Article article, Article article2) {
        this.template.convertAndSend("/article-db", new ComparisonData(article, article2));
    }

}
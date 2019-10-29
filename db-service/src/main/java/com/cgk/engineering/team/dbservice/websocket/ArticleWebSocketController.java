package com.cgk.engineering.team.dbservice.websocket;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.dbservice.repository.ArticleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class ArticleWebSocketController {

    private SimpMessagingTemplate template;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    public ArticleWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/article")
    public void getArticles() throws Exception {
        articleRepository.getAllStream().parallel().forEach(this::sendArticle);
    }


    public void sendArticle(Article article) {
        this.template.convertAndSend("/article-db", article);
    }

}
package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.dbservice.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleWebSocketController {

    private SimpMessagingTemplate template;

    @Autowired
    public ArticleWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendArticle(Article article) {
        this.template.convertAndSend("/article", article);
    }

}
package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.FastComparison;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ComparisonWebSocketController {

    private SimpMessagingTemplate template;

    @Autowired
    DatabaseServiceClient dbClient;

    @Autowired
    AlgorithmClient algorithmClient;

    @Autowired
    public ComparisonWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/compare/{articleID}")
    public void compareArticle(@DestinationVariable ObjectId articleID) throws Exception {
        try {
            List<Article> articles = dbClient.getArticles();
            Article article = dbClient.getArticle(articleID);
            articles.remove(article);
            for(Article theArticle : articles){
                sendComparison(algorithmClient.getFastComparison(new ComparisonData(article, theArticle)));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void sendComparison(FastComparison comparison) {
        this.template.convertAndSend("/article/comparison", comparison);
    }

}
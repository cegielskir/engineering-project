package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class ComparisonWebSocketController {

    private static final String DB_WEBSOCKET_URL = "ws://localhost:9092/dupsko";

    private SimpMessagingTemplate template;

    @Autowired
    StompSessionHandler stompSessionHandler;

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
            //System.out.println("comapare");
            List<Article> articles = dbClient.getArticles();
            Article article = dbClient.getArticle(articleID);
            articles.remove(article);
            articles = articles.stream()
                    .filter(art -> !art.get_id().equals(articleID.toString()))
                    .collect(Collectors.toList());
            for(Article theArticle : articles){
                ComparisonData comparisonData = new ComparisonData(article, theArticle);
                comparisonData.setMetric("Dice");
                sendComparison(algorithmClient.getComparisonWithChosenMetric(comparisonData));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

//    @MessageMapping("/compare/{articleID}")
//    public void compareArticle(@DestinationVariable ObjectId articleID) throws Exception {
//        WebSocketClient client = new StandardWebSocketClient();
//        WebSocketStompClient stompClient = new WebSocketStompClient(client);
//
//        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//        ListenableFuture<StompSession> future = stompClient.connect(DB_WEBSOCKET_URL, stompSessionHandler);
//        StompSession session = future.get();
//        stompSessionHandler.initMessage(articleID, session);
//    }


    public void sendComparison(BasicComparison comparison) {
        //System.out.println("send comparison");
        if(comparison.getPercentage() > 10) {
            this.template.convertAndSend("/article/comparison", comparison);
        }
    }

}
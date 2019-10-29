package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.FastComparison;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class ComparisonWebSocketController {

    private static final String DB_WEBSOCKET_URL = "ws://localhost:9092/article-db";

    private SimpMessagingTemplate template;

    @Autowired
    StompSessionHandler sessionHandler;

    @Autowired
    DatabaseServiceClient dbClient;

    @Autowired
    public ComparisonWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

//    @MessageMapping("/compare/{articleID}")
//    public void compareArticle(@DestinationVariable ObjectId articleID) throws Exception {
//        try {
//            List<Article> articles = dbClient.getArticles();
//            Article article = dbClient.getArticle(articleID);
//            articles.remove(article);
//            for(Article theArticle : articles){
//                sendComparison(algorithmClient.getFastComparison(new ComparisonData(article, theArticle)));
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }

    @MessageMapping("/compare/{articleID}")
    public void compareArticle(@DestinationVariable ObjectId articleID) throws Exception {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        ListenableFuture<StompSession> future = stompClient.connect(DB_WEBSOCKET_URL, sessionHandler);
        StompSession session = future.get();
        sessionHandler.initMessage(articleID, session);
    }


    public void sendComparison(FastComparison comparison) {
        this.template.convertAndSend("/article/comparison", comparison);
    }

}
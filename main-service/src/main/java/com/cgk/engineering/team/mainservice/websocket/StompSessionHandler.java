package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class StompSessionHandler extends StompSessionHandlerAdapter {

    private final Article article;

    @Autowired
    AlgorithmClient algorithmClient;

    @Autowired
    private ComparisonWebSocketController comparisonWebSocketController;

    public StompSessionHandler(Article article) {
        this.article = article;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/article-db", this);
        session.send("/db/compare", "");
    }


    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Article.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Runnable runnable =
            () -> {
                Article articleToCompare = (Article) payload;
                comparisonWebSocketController.sendComparison(
                        algorithmClient.getFastComparison(new ComparisonData(article, articleToCompare)));

            };
        new Thread(runnable).start();
    }
}
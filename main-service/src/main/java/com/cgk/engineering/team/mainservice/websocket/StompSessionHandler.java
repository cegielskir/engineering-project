package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.FastComparison;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Component
public class StompSessionHandler extends StompSessionHandlerAdapter {

    @Autowired
    AlgorithmClient algorithmClient;

    @Autowired
    private ComparisonWebSocketController comparisonWebSocketController;

    public Type getPayloadType(StompHeaders headers) {
        return ComparisonData.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Runnable runnable =
            () -> {
                ComparisonData comparisonData = (ComparisonData) payload;
                comparisonWebSocketController.sendComparison(algorithmClient.getFastComparison(comparisonData));
            };
        new Thread(runnable).start();
    }

    public void initMessage(ObjectId articleId, StompSession session){
        session.subscribe("/article-db", this);
        session.send("/db/article/" + articleId, "");
    }
}
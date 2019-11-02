package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.model.BasicComparison;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

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
        new Thread(new FrameHandlerTask(payload)).start();
    }

    public void initMessage(ObjectId articleId, StompSession session){
        session.subscribe("/article-db", this);
        session.send("/db/article/" + articleId, "");
    }

    class FrameHandlerTask implements Runnable {
        Object payload;

        FrameHandlerTask(Object payload) { this.payload = payload; }
        public void run() {
            ComparisonData comparisonData = (ComparisonData) payload;
            comparisonData.setMetric("Dice"); //USUN TO!
            comparisonWebSocketController.sendComparison(algorithmClient.getComparisonWithChosenMetric(comparisonData));
        }
    }
}
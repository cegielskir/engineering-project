package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.model.BasicComparison;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import io.micrometer.core.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StompSessionHandler extends StompSessionHandlerAdapter {

    @Autowired
    AlgorithmClient algorithmClient;

    @Autowired
    private ComparisonWebSocketController comparisonWebSocketController;

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return ComparisonData.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        try {
            ComparisonData comparisonData = (ComparisonData) payload;
            comparisonData.setMetric("Dice"); //USUN TO!
            comparisonWebSocketController.sendComparison(new BasicComparison()/*algorithmClient.getComparisonWithChosenMetric(comparisonData)*/);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void handleException(StompSession session,
                                @Nullable StompCommand command,
                                StompHeaders headers,
                                byte[] payload,
                                Throwable exception) {


    }

    public void handleTransportError(StompSession session,
                                     Throwable exception) {
        exception.printStackTrace();
    }

    public void initMessage(ObjectId articleId, StompSession session) {
        session.subscribe("/article-db", this);
        session.send("/db/article/" + articleId, "");
    }
}
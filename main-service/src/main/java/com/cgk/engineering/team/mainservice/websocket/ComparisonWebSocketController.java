package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.comparison.ComparisonServiceController;
import com.cgk.engineering.team.mainservice.client.comparison.services.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.*;
import com.cgk.engineering.team.mainservice.utills.ConfigProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Controller
public class ComparisonWebSocketController {
    private SimpMessagingTemplate template;

    @Autowired
    DatabaseServiceClient dbClient;

    @Autowired
    ComparisonServiceController comparisonServiceController;

    private Disposable currentSubscription;

    @Autowired
    ConfigProvider configProvider;

    @Autowired
    public ComparisonWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/compare/{articleID}/{threshold}/{metric}")
    public void compareArticle(@DestinationVariable("articleID") ObjectId articleID,
                               @DestinationVariable("threshold") int threshold,
                               @DestinationVariable("metric") String metric){

        String dbUrl = System.getenv("DB_URL") == null ? "http://localhost}:9092": System.getenv("DB_URL");
        WebClient client = WebClient.create(dbUrl);

        Flux<ComparisonData> comparisonDataFlux = client.get()
                .uri("/article/stream/" + articleID)
                .retrieve()
                .bodyToFlux(ComparisonData.class);

        currentSubscription = comparisonDataFlux.subscribe(comparisonData -> {
            if(comparisonData.getBasicComparison() != null){
                sendComparison(comparisonData.getBasicComparison(), threshold);
            } else {
                comparisonData.setMetric(metric);
                BasicComparison basicComparison = comparisonServiceController.getBasicComparison(comparisonData);
                dbClient.addComparison(basicComparison);
                sendComparison(basicComparison, threshold);
            }
        });
    }

    @MessageMapping("/compare/dispose")
    public void compareArticle(){
        this.currentSubscription.dispose();
    }



    public void sendComparison(BasicComparison comparison, int threshold) {
        if(comparison.getPercentage() > threshold) {
            this.template.convertAndSend("/article/comparison", comparison);
        }
    }

}
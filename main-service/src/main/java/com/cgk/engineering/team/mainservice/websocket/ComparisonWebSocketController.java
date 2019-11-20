package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.comparison.ComparisonServiceController;
import com.cgk.engineering.team.mainservice.client.comparison.services.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
public class ComparisonWebSocketController {
    private SimpMessagingTemplate template;

    @Autowired
    DatabaseServiceClient dbClient;

    @Autowired
    ComparisonServiceController comparisonServiceController;

    private Disposable currentSubscription;

    @Autowired
    public ComparisonWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/compare/{articleID}/{threshold}/{metric}")
    public void compareArticle(@DestinationVariable("articleID") ObjectId articleID,
                               @DestinationVariable("threshold") int threshold,
                               @DestinationVariable("metrics") List<String> metrics){

        String dbUrl = System.getenv("DB_URL") == null ? "http://localhost:9092": System.getenv("DB_URL");
        WebClient client = WebClient.create(dbUrl);

        Flux<ComparisonData> comparisonDataFlux = client.get()
            .uri("/article/stream/" + articleID)
            .retrieve()
            .bodyToFlux(ComparisonData.class);

        currentSubscription = comparisonDataFlux.subscribe(
            comparisonData -> {
                for(String metric : metrics) {
                    comparisonData.setMetric(metric);
                    BasicComparison basicComparison = comparisonServiceController.getBasicComparison(comparisonData);
                    dbClient.addComparison(basicComparison);
                    sendComparison(basicComparison, threshold);
                }
            },
            this::sendComparisonError,
            this::sendComparisonComplete);
    }

    private void sendComparison(BasicComparison comparison, int threshold) {
        if(comparison.getPercentage() > threshold) {
            this.template.convertAndSend("/article/comparison", comparison);
        }
    }

    @MessageMapping("/compare/dispose")
    public void compareArticle(){
        this.currentSubscription.dispose();
    }

    private void sendComparisonError(Throwable error){
        this.template.convertAndSend("/article/comparison",
                "Error occurred while comparing articles: " + error);
    }

    private void sendComparisonComplete(){
        this.template.convertAndSend("/article/comparison",
                "Comparing articles has been finished");
    }

}
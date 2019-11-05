package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

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
    public void compareArticle(@PathVariable("articleID") ObjectId articleID,
                               @RequestParam(value = "threshold", required = false) int threshold,
                               @RequestParam(value = "metric", required = false) String metric){

        WebClient client = WebClient.create("http://localhost:9092");

        Flux<ComparisonData> comparisonDataFlux = client.get()
                .uri("/article/stream/" + articleID)
                .retrieve()
                .bodyToFlux(ComparisonData.class);

        comparisonDataFlux.subscribe(comparisonData -> {
            if(comparisonData.getBasicComparison() != null){
                sendComparison(comparisonData.getBasicComparison(), threshold);
            } else {
                comparisonData.setMetric(metric);
                BasicComparison basicComparison = algorithmClient.getComparisonWithChosenMetric(comparisonData);
                dbClient.addComparison(basicComparison);
                sendComparison(basicComparison, threshold);
            }
        });
    }


    public void sendComparison(BasicComparison comparison, int threshold) {
        if(comparison.getPercentage() > threshold) {
            this.template.convertAndSend("/article/comparison", comparison);
        }
    }

}
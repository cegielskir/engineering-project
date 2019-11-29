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

    private Disposable currentComparisonSubscription;
    private Disposable currentDbSubscription;

    @Autowired
    public ComparisonWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/compare/{articleID}/{threshold}/{articleIDSToCompare}/{metrics}")
    public void compareArticle(@DestinationVariable("articleID") ObjectId articleID,
                               @DestinationVariable("threshold") int threshold,
                               @DestinationVariable("metrics") List<String> metrics,
                               @DestinationVariable("articleIDSToCompare") List<String> articleIDS){

        String dbUrl = System.getenv("DB_URL") == null ? "http://localhost:9092": System.getenv("DB_URL");
        WebClient client = WebClient.create(dbUrl);

        Flux<ComparisonData> comparisonDataFlux = client.get()
            .uri("/article/stream/" + articleID + "/" + String.join(",", articleIDS) + "/" + String.join(",", metrics))
            .retrieve()
            .bodyToFlux(ComparisonData.class);

        Flux<BasicComparison> basicComparisonFlux = client.get()
                .uri("/basic-comparison/stream/" + articleID + "/" + String.join(",", articleIDS) + "/" + String.join(",", metrics))
                .retrieve()
                .bodyToFlux(BasicComparison.class);

        currentComparisonSubscription = comparisonDataFlux.subscribe(
            comparisonData -> {
                for(String metric : metrics) {
                    comparisonData.setMetric(metric);
                    BasicComparison basicComparison = comparisonServiceController.getBasicComparison(comparisonData);
                    basicComparison.setSecondArticleTitle(comparisonData.getArticle2().getTitle());
                    basicComparison.setSecondArticleDescription(comparisonData.getArticle2().getDescription());
                    dbClient.addComparison(basicComparison);
                    if(basicComparison.getPercentage() > threshold) {
                        sendComparison(basicComparison);
                    }
                }
            },
            this::sendComparisonError,
            this::sendComparisonComplete
        );

        currentDbSubscription = basicComparisonFlux.subscribe(
            this::sendComparison,
            this::sendDbError,
            this::sendDbComplete
        );
    }

    private void sendComparison(BasicComparison comparison) {
        this.template.convertAndSend("/article/comparison", comparison);
    }

    @MessageMapping("/compare/dispose")
    public void compareArticle(){
        this.currentComparisonSubscription.dispose();
        this.currentDbSubscription.dispose();
    }

    private void sendComparisonError(Throwable error){
        error.printStackTrace();
        this.template.convertAndSend("/article/comparison",
                new BasicResponse("ERROR", "Error occurred while comparing articles."));
    }

    private void sendDbError(Throwable error){
        error.printStackTrace();
        this.template.convertAndSend("/article/comparison",
                new BasicResponse("ERROR", "Error occurred while getting comparisons from db."));
    }

    private void sendComparisonComplete(){
        this.template.convertAndSend("/article/comparison",
                new BasicResponse("SUCCESS", "Comparing articles has been finished"));
    }

    private void sendDbComplete(){
        this.template.convertAndSend("/article/comparison",
                new BasicResponse("SUCCESS", "Getting comparisons from DB completed"));
    }

}
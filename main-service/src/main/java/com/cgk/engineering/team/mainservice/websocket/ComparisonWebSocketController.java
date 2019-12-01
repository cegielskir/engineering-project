package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.comparison.ComparisonServiceController;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Controller
public class ComparisonWebSocketController {
    private SimpMessagingTemplate template;

    @Autowired
    DatabaseServiceClient dbClient;

    @Autowired
    ComparisonServiceController comparisonServiceController;

    private Disposable currentComparisonSubscription;

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

        currentComparisonSubscription = comparisonDataFlux.parallel().runOn(Schedulers.parallel()).subscribe(
            comparisonData -> {
                boolean isSomethingNew = false;
                metrics.stream()
                    .filter(metric -> comparisonData.getComparisonMap().get(metric) == null)
                    .forEach(metric -> {
                        ComparisonRequest comparisonRequest = new ComparisonRequest(comparisonData.getArticle1(), comparisonData.getArticle2(), metric);
                        BasicComparison basicComparison = comparisonServiceController.getBasicComparison(comparisonRequest);
                        comparisonData.addComparison(metric, basicComparison.getPercentage());
                    });
                dbClient.addComparisonData(comparisonData);
                sendComparison(createBasicComparisonResponse(comparisonData), threshold);
            },
            this::sendComparisonError,
            this::sendComparisonComplete
        );
    }

    private void sendComparison(BasicComparisonResponse comparison, int threshold) {
        this.template.convertAndSend("/article/comparison", comparison);
    }

    @MessageMapping("/compare/dispose")
    public void compareArticle(){
        this.currentComparisonSubscription.dispose();
    }

    private void sendComparisonError(Throwable error){
        error.printStackTrace();
        this.template.convertAndSend("/article/comparison",
                new BasicResponse("ERROR", "Error occurred while comparing articles."));
    }

    private void sendComparisonComplete(){
        this.template.convertAndSend("/article/comparison",
                new BasicResponse("SUCCESS", "Comparing articles has been finished"));
    }

    private BasicComparisonResponse createBasicComparisonResponse(ComparisonData comparisonData){
        BasicComparisonResponse basicComparisonResponse = new BasicComparisonResponse();
        Article secondArticle = comparisonData.getArticle2();
        basicComparisonResponse.setComparisonMap(comparisonData.getComparisonMap());
        basicComparisonResponse.setSecondArticleDescription(secondArticle.getDescription());
        basicComparisonResponse.setSecondArticleShortContent(secondArticle.getContent().length() > 300
                ? secondArticle.getContent().substring(0, 300) + "..."
                : secondArticle.getContent());
        return basicComparisonResponse;
    }
}
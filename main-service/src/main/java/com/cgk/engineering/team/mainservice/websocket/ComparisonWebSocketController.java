package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.*;
import com.cgk.engineering.team.mainservice.utills.ConfigProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
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
    AlgorithmClient algorithmClient;

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

        WebClient client = WebClient.create(configProvider.getPropValues());

        Flux<ComparisonData> comparisonDataFlux = client.get()
                .uri("/article/stream/" + articleID)
                .retrieve()
                .bodyToFlux(ComparisonData.class);

        currentSubscription = comparisonDataFlux.subscribe(comparisonData -> {
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

    private void sendComparison(BasicComparison comparison, int threshold) {
        if(comparison.getPercentage() > threshold) {
            this.template.convertAndSend("/article/comparison", comparison);
        }
    }

    @MessageMapping("/compare/dispose")
    public void compareArticle(){
        this.currentSubscription.dispose();
    }

    @MessageMapping("/find/{searchedKey}")
    public void findArticle(@DestinationVariable("searchedKey") String searchedKey){
        System.out.println("zaczynam szukac");
        sendSearchedResult(dbClient.findArticles(searchedKey));
    }

    private void sendSearchedResult(List<Article> articles) {
        this.template.convertAndSend("/article/searchEngine", articles);
    }

}
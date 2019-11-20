package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.client.comparison.ComparisonServiceController;
import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.BasicComparison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

public class ArticleWebSocketController {
    private SimpMessagingTemplate template;

    @Autowired
    DatabaseServiceClient dbClient;

    private Disposable currentSubscription;

    @Autowired
    public ArticleWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/article/find/content/{partOfContent}")
    public void findAdrticleByContent(@DestinationVariable("partOfContent") String partOfContent){

        findArticle(partOfContent, "content");
    }

    @MessageMapping("/article/find/title/{partOfTitle}")
    public void findArticleByTitle(@DestinationVariable("partOfTitle") String partOfTitle){

        findArticle(partOfTitle, "title");
    }

    private void findArticle(String partOfArticle, String findBy) {
        String dbUrl = System.getenv("DB_URL") == null ? "http://localhost:9092": System.getenv("DB_URL");
        WebClient client = WebClient.create(dbUrl);

        Flux<Article> articleFlux = client.get()
                .uri("/article/find/"+ findBy + "/" + partOfArticle)
                .retrieve()
                .bodyToFlux(Article.class);

        currentSubscription = articleFlux.subscribe(
                this::sendFindArticle,
                this::sendFindArticleError,
                this::sendFindArticleComplete
            );
    }

    private void sendFindArticle(Article article){
        this.template.convertAndSend("/article/find", article);
    }

    private void sendFindArticleError(Throwable error){
        this.template.convertAndSend("/article/find",
                "Error occurred while finding articles: " + error);
    }

    private void sendFindArticleComplete(){
        this.template.convertAndSend("/article/find",
                "Finding articles has been finished");
    }

    @MessageMapping("/article/dispose")
    public void disposeFindArticles(){
        this.currentSubscription.dispose();
    }
}

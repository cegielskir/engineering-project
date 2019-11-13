package com.cgk.engineering.team.dbservice.repository;

import com.cgk.engineering.team.dbservice.model.Article;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;
import java.util.List;

@Repository
public interface ReactiveArticleRepository extends ReactiveCrudRepository<Article, String> {
    Article findByTitle(String title);

    List<Article> findArticlesByContentContains(String phrase);
}
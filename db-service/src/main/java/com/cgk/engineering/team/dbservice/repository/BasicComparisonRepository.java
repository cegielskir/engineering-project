package com.cgk.engineering.team.dbservice.repository;

import com.cgk.engineering.team.dbservice.model.BasicComparison;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Repository
public interface BasicComparisonRepository extends ReactiveMongoRepository<BasicComparison, String> {

    Flux<BasicComparison> findAllByArticleIDsContainsAndArticleIDsIsContainingAndMetricIsContaining(String articleId, List<String> articleIDs, List<String> metrics);
    Flux<BasicComparison> findAllByArticleIDsContainsAndMetricIsContaining(String articleId, List<String> metrics);
    Mono<BasicComparison> findByArticleIDsContainsAndMetricIs(List<String> articleIDs, String metric);
}

package com.cgk.engineering.team.dbservice.repository;

import com.cgk.engineering.team.dbservice.model.BasicComparison;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BasicComparisonRepository extends ReactiveMongoRepository<BasicComparison, String> {

    Mono<BasicComparison> findByFirstArticleIDAndSecondArticleID(String firstArticleId, String secondArticleId);


}

package com.cgk.engineering.team.dbservice.repository;

import com.cgk.engineering.team.dbservice.model.BasicComparison;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Repository
public interface BasicComparisonRepository extends ReactiveMongoRepository<BasicComparison, String> {

    Flux<BasicComparison> findAllByArticleIDsInAndMetricIn(List<String> articleIDs, List<String> metrics);
    Flux<BasicComparison> findAllByArticleIDsContainsAndMetricIn(String articleId, Set<String> metrics);
    Mono<BasicComparison> findFirstByArticleIDsIsAndMetricIs(Set<String> articleIDs, String metric);
}

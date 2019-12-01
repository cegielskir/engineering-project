package com.cgk.engineering.team.dbservice.repository;

import com.cgk.engineering.team.dbservice.model.ComparisonData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Set;

@Repository
public interface ComparisonDataRepository extends ReactiveMongoRepository<ComparisonData, String> {

    Mono<ComparisonData> findFirstByArticleIDsIn(Set<String> articleIDs);
}

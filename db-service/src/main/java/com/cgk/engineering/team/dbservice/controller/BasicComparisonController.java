package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.BasicComparison;
import com.cgk.engineering.team.dbservice.repository.BasicComparisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/basic-comparison")
public class BasicComparisonController {

    @Autowired
    BasicComparisonRepository basicComparisonRepository;

    @PostMapping
    public Mono<BasicComparison> addComparison(@RequestBody BasicComparison basicComparison){
        return basicComparisonRepository.save(basicComparison);
    }

    @GetMapping(value= "/stream/{articleId}/{articleIDSToCompare}/{metrics}", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BasicComparison> getComparisons(@PathVariable("articleId") String articleId,
                                                @PathVariable("articleIDSToCompare") List<String> articleIDS,
                                                @PathVariable("metrics") List<String> metrics) {
        if(articleIDS.size() > 1) {
            return basicComparisonRepository
                    .findAllByArticleIDsInAndMetricIn(articleIDS, metrics);
        } else {
            return basicComparisonRepository
                    .findAllByArticleIDsContainsAndMetricIn(articleId, new HashSet<>(metrics));
        }
    }
}

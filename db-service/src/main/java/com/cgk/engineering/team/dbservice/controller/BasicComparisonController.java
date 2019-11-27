package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.BasicComparison;
import com.cgk.engineering.team.dbservice.repository.BasicComparisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/basic-comparison")
public class BasicComparisonController {

    @Autowired
    BasicComparisonRepository basicComparisonRepository;

    @PostMapping
    public Mono<BasicComparison> addComparison(@RequestBody BasicComparison basicComparison){
        return basicComparisonRepository.save(basicComparison);
    }
    @GetMapping
    public Mono<BasicComparison> getComparison(@RequestParam String id1,
                                               @RequestParam String id2, @RequestParam String metric){
        return basicComparisonRepository.
                findByFirstArticleIDAndSecondArticleIDAndMetric(id1, id2, metric)
                .switchIfEmpty(
                        basicComparisonRepository
                                .findByFirstArticleIDAndSecondArticleIDAndMetric(id2, id1, metric));
    }
}

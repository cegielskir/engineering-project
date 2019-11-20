package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.BasicComparison;
import com.cgk.engineering.team.dbservice.repository.BasicComparisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}

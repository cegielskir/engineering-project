package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.ComparisonData;
import com.cgk.engineering.team.dbservice.repository.ComparisonDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comparison-data")
public class ComparisonDataController {

    @Autowired
    ComparisonDataRepository comparisonDataRepository;

    @PostMapping
    public Mono<ComparisonData> addComparisonData(@RequestBody ComparisonData comparisonData){
        return comparisonDataRepository.save(comparisonData);
    }
}

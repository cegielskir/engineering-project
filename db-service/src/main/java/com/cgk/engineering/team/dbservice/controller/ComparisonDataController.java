package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.model.BasicComparison;
import com.cgk.engineering.team.dbservice.model.ComparisonData;
import com.cgk.engineering.team.dbservice.repository.BasicComparisonRepository;
import com.cgk.engineering.team.dbservice.repository.ComparisonDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;

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

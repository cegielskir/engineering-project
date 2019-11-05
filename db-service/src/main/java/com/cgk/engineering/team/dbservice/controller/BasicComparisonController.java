package com.cgk.engineering.team.dbservice.controller;

import com.cgk.engineering.team.dbservice.repository.BasicComparisonRepository;
import com.cgk.engineering.team.mainservice.model.BasicComparison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-comparison")
public class BasicComparisonController {

    @Autowired
    BasicComparisonRepository basicComparisonRepository;

    @PostMapping
    public BasicComparison addComparison(@RequestBody BasicComparison basicComparison){
        basicComparisonRepository.save(basicComparison);
        return basicComparison;
    }
}

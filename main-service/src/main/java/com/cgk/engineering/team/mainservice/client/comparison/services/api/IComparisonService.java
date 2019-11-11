package com.cgk.engineering.team.mainservice.client.comparison.services.api;

import com.cgk.engineering.team.mainservice.model.BasicComparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.ComparisonMethod;
import com.cgk.engineering.team.mainservice.model.DetailsComparison;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface IComparisonService {

    @PostMapping("/algorithm/detailed")
    DetailsComparison getDetailedComparison(@RequestBody ComparisonData comparisonData);

    @PostMapping("/algorithm/basic")
    BasicComparison getBasicComparison(@RequestBody ComparisonData comparisonData);

    @GetMapping("/method")
    List<ComparisonMethod> getAvailableMethods();
}

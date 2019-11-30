package com.cgk.engineering.team.mainservice.client.comparison.services.api;

import com.cgk.engineering.team.mainservice.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface IComparisonService {

    @PostMapping("/algorithm/detailed")
    DetailedComparison getDetailedComparison(@RequestBody ComparisonRequest comparisonRequest);

    @PostMapping("/algorithm/basic")
    BasicComparison getBasicComparison(@RequestBody ComparisonRequest comparisonRequest);

    @GetMapping("/method")
    List<ComparisonMethod> getAvailableMethods();
}

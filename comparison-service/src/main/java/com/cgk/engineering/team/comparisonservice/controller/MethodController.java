package com.cgk.engineering.team.comparisonservice.controller;

import com.cgk.engineering.team.comparisonservice.algorithm.metrics.EditBasedMetrics;
import com.cgk.engineering.team.comparisonservice.algorithm.metrics.TokenBasedMetrics;
import com.cgk.engineering.team.comparisonservice.model.ComparisonMethod;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/method")
public class MethodController {
    private static final String EDIT_BASED = "EditBased";
    private static final String TOKEN_BASED = "TokenBased";

    @GetMapping()
    List<ComparisonMethod> getAvailableMethods() {
        List<ComparisonMethod> comparisonMethods = Arrays.stream(EditBasedMetrics.values())
                .map(metricType -> new ComparisonMethod(metricType.name(),
                                                        EDIT_BASED,
                                    true))
                .collect(Collectors.toList());
        comparisonMethods.addAll(Arrays.stream(TokenBasedMetrics.values())
                .map(metricType -> new ComparisonMethod(metricType.name(),
                        TOKEN_BASED,
                        true))
                .collect(Collectors.toList())
        );

        return comparisonMethods;
    }

}

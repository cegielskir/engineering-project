package com.cgk.engineering.team.mainservice.client;

import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "simpleclient-service")
public interface AlgorithmClient {

    @GetMapping("/algorithm")
    Comparison getComparison(@RequestBody ComparisonData comparisonData);
}

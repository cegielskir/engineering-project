package com.cgk.engineering.team.mainservice.client;

import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.FastComparison;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "simpleclient-service")
public interface AlgorithmClient {

    @PostMapping("/algorithm/accurate")
    Comparison getComparison(@RequestBody ComparisonData comparisonData);

    @PostMapping("/algorithm/fast")
    FastComparison getFastComparison(@RequestBody ComparisonData comparisonData);
}

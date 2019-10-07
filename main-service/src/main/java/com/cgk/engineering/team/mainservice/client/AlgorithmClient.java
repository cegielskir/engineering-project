package com.cgk.engineering.team.mainservice.client;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.FastComparison;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "simpleclient-service")
public interface AlgorithmClient {

    @GetMapping("/algorithm/accurate")
    Comparison getComparison(@RequestBody ComparisonData comparisonData);

    @GetMapping("/algorithm/fast")
    FastComparison getFastComparison(@RequestBody ComparisonData comparisonData);
}

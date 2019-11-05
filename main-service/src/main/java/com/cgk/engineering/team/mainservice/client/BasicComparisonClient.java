package com.cgk.engineering.team.mainservice.client;

import com.cgk.engineering.team.mainservice.model.BasicComparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.DetailsComparison;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "db-service")
public interface BasicComparisonClient {

    @PostMapping("/basic-comparison")
    BasicComparison addComparison(@RequestBody BasicComparison basicComparison);
}

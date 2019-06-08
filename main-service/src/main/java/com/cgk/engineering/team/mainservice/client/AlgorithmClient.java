package com.cgk.engineering.team.mainservice.client;

import com.cgk.engineering.team.mainservice.model.Comparison;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "simpleclient-service")
public interface AlgorithmClient {

    @GetMapping("/algorithm/{articleId}")
    Comparison getComparison(@RequestParam("id_1") ObjectId id1, @RequestParam("id_2") ObjectId id2);
}

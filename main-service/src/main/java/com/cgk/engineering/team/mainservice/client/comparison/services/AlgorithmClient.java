package com.cgk.engineering.team.mainservice.client.comparison.services;

import com.cgk.engineering.team.mainservice.client.comparison.services.api.IComparisonService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "simpleclient-service")
public interface AlgorithmClient extends IComparisonService {

}

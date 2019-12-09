package com.cgk.engineering.team.mainservice.client.comparison;

import com.cgk.engineering.team.mainservice.client.comparison.util.ComparisonServicesUtil;
import com.cgk.engineering.team.mainservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComparisonServiceController {

    @Autowired
    ComparisonServicesUtil comparisonServicesUtil;

    public BasicComparison getBasicComparison(ComparisonRequest comparisonRequest){
        return comparisonServicesUtil
                .getServiceWithMethod(comparisonRequest.getMetric())
                .getBasicComparison(comparisonRequest);
    }

    public DetailedComparison getDetailedComparison(ComparisonRequest comparisonRequest){
        return comparisonServicesUtil
                .getServiceWithMethod(comparisonRequest.getMetric())
                .getDetailedComparison(comparisonRequest);
    }

    public List<ComparisonMethod> getAvailableBasicMethods(){
        return comparisonServicesUtil.getAndUpdateAvailableBasicMethods();
    }

    public List<String> getAvailableDetailedMethods(){
        return  comparisonServicesUtil.getAvailableDetailedMethods();
    }
}

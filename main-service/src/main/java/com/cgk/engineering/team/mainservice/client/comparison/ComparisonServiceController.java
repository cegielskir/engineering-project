package com.cgk.engineering.team.mainservice.client.comparison;

import com.cgk.engineering.team.mainservice.client.comparison.util.ComparisonServicesUtil;
import com.cgk.engineering.team.mainservice.model.BasicComparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.model.ComparisonMethod;
import com.cgk.engineering.team.mainservice.model.DetailsComparison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComparisonServiceController {

    @Autowired
    ComparisonServicesUtil comparisonServicesUtil;

    public BasicComparison getBasicComparison(ComparisonData comparisonData){
        return comparisonServicesUtil
                .getServiceWithMethod(comparisonData.getMetric())
                .getBasicComparison(comparisonData);
    }

    public DetailsComparison getDetailedComparison(ComparisonData comparisonData){
        return comparisonServicesUtil
                .getServiceWithMethod(comparisonData.getMetric())
                .getDetailedComparison(comparisonData);
    }

    public List<ComparisonMethod> getAvailableBasicMethods(){
        return comparisonServicesUtil.getAndUpdateAvailableBasicMethods();
    }

    public List<String> getAvailableDetailedMethods(){
        return  comparisonServicesUtil.getAvailableDetailedMethods();
    }
}

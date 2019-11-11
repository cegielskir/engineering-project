package com.cgk.engineering.team.mainservice.model;

import com.cgk.engineering.team.mainservice.client.comparison.services.api.IComparisonService;

public class ComparisonMethodInfo {

    private IComparisonService comparisonService;
    private ComparisonMethod comparisonMethod;

    public ComparisonMethodInfo(IComparisonService comparisonService, ComparisonMethod comparisonMethod) {
        this.comparisonService = comparisonService;
        this.comparisonMethod = comparisonMethod;
    }

    public IComparisonService getComparisonService() {
        return comparisonService;
    }

    public void setComparisonService(IComparisonService comparisonService) {
        this.comparisonService = comparisonService;
    }

    public ComparisonMethod getComparisonMethod() {
        return comparisonMethod;
    }

    public void setComparisonMethod(ComparisonMethod comparisonMethod) {
        this.comparisonMethod = comparisonMethod;
    }
}

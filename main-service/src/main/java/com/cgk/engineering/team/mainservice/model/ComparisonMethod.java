package com.cgk.engineering.team.mainservice.model;

import com.cgk.engineering.team.mainservice.client.comparison.services.api.IComparisonService;

public class ComparisonMethod {

    private String name;
    private boolean isDetailedMethodAvailable;
    private IComparisonService comparisonService;

    public ComparisonMethod() {}

    public ComparisonMethod(String name, boolean isDetailedMethodAvailable) {
        this.name = name;
        this.isDetailedMethodAvailable = isDetailedMethodAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDetailedMethodAvailable() {
        return isDetailedMethodAvailable;
    }

    public void setDetailedMethodAvailable(boolean detailedMethodAvailable) {
        isDetailedMethodAvailable = detailedMethodAvailable;
    }

    public IComparisonService getComparisonService() {
        return comparisonService;
    }

    public void setComparisonService(IComparisonService comparisonService) {
        this.comparisonService = comparisonService;
    }
}

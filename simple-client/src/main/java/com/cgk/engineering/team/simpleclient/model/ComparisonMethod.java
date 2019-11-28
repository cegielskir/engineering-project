package com.cgk.engineering.team.simpleclient.model;

public class ComparisonMethod {

    private String name;
    private String metricType;
    private boolean isDetailedMethodAvailable;

    public ComparisonMethod() {}

    public ComparisonMethod(String name, String metricType, boolean isDetailedMethodAvailable) {
        this.name = name;
        this.metricType = metricType;
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

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }
}


package com.cgk.engineering.team.comparisonservice.model;

public abstract class AbstractComparison {

    private int percentage;
    private String metric;

    public AbstractComparison() {}

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}


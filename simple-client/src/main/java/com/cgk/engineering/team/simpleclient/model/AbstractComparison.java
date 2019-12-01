package com.cgk.engineering.team.simpleclient.model;

import org.springframework.data.annotation.Id;

public abstract class AbstractComparison {

    private int percentage;
    private String metric;

    public AbstractComparison() {}

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        if(percentage<=100 && percentage>=0)
            this.percentage = percentage;
        else throw new IllegalArgumentException();
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}


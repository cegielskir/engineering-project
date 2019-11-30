package com.cgk.engineering.team.mainservice.model;

public class BasicComparisonResponse {

    private String secondArticleShortContent;
    private String secondArticleTitle;
    private String secondArticleDescription;
    private int percentage;
    private String metric;

    public BasicComparisonResponse() {}

    public String getSecondArticleShortContent() {
        return secondArticleShortContent;
    }

    public void setSecondArticleShortContent(String secondArticleShortContent) {
        this.secondArticleShortContent = secondArticleShortContent;
    }

    public String getSecondArticleTitle() {
        return secondArticleTitle;
    }

    public void setSecondArticleTitle(String secondArticleTitle) {
        this.secondArticleTitle = secondArticleTitle;
    }

    public String getSecondArticleDescription() {
        return secondArticleDescription;
    }

    public void setSecondArticleDescription(String secondArticleDescription) {
        this.secondArticleDescription = secondArticleDescription;
    }

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

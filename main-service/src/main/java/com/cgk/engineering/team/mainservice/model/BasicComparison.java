package com.cgk.engineering.team.mainservice.model;

public class BasicComparison extends Comparison {
    private String firstArticleID;
    private String secondArticleID;
    private String secondArticleTitle;
    private String secondArticleDescription;
    private String secondArticleShortContent;
    private String metric;

    public BasicComparison() {}

    public void setArticleIDs(String firstArticleID, String secondArticleID) {
        this.firstArticleID = firstArticleID;
        this.secondArticleID = secondArticleID;
    }

    public String getFirstArticleID() {
        return firstArticleID;
    }

    public void setFirstArticleID(String firstArticleID) {
        this.firstArticleID = firstArticleID;
    }

    public String getSecondArticleID() {
        return secondArticleID;
    }

    public void setSecondArticleID(String secondArticleID) {
        this.secondArticleID = secondArticleID;
    }

    public String getSecondArticleShortContent() {
        return secondArticleShortContent;
    }

    public void setSecondArticleShortContent(String secondArticleShortContent) {
        this.secondArticleShortContent = secondArticleShortContent;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
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
}


package com.cgk.engineering.team.mainservice.model;

import java.util.Map;

public class BasicComparisonResponse {
    private String secondArticleID;
    private String secondArticleShortContent;
    private String secondArticleTitle;
    private String secondArticleDescription;
    private Map<String, Integer> comparisonMap;

    public BasicComparisonResponse() {}

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

    public Map<String, Integer> getComparisonMap() {
        return comparisonMap;
    }

    public void setComparisonMap(Map<String, Integer> comparisonMap) {
        this.comparisonMap = comparisonMap;
    }
}

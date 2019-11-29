package com.cgk.engineering.team.dbservice.model;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BasicComparison extends Comparison {
    private String firstArticleID;

    @Indexed
    private Set<String> articleIDs;
    private String secondArticleTitle;
    private String secondArticleDescription;
    private String secondArticleShortContent;
    private String metric;

    public BasicComparison() {}

    public void setArticleIDs(String firstArticleID, String secondArticleID) {
        this.firstArticleID = firstArticleID;
        this.articleIDs = new HashSet<>(Arrays.asList(firstArticleID, secondArticleID));
    }

    public String getFirstArticleID() {
        return firstArticleID;
    }

    public void setFirstArticleID(String firstArticleID) {
        this.firstArticleID = firstArticleID;
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

    public Set<String> getArticleIDs() {
        return articleIDs;
    }

    public void setArticleIDs(Set<String> articleIDs) {
        this.articleIDs = articleIDs;
    }
}

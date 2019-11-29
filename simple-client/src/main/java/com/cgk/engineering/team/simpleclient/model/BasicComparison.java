package com.cgk.engineering.team.simpleclient.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BasicComparison extends Comparison {
    private Set<String> articleIDs;
    private String secondArticleTitle;
    private String secondArticleDescription;
    private String secondArticleShortContent;

    public BasicComparison() {}

    public void setArticleIDs(String firstArticleID, String secondArticleID) {
        this.articleIDs = new HashSet<>(Arrays.asList(firstArticleID, secondArticleID));
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

    public Set<String> getArticleIDs() {
        return articleIDs;
    }

    public void setArticleIDs(Set<String> articleIDs) {
        this.articleIDs = articleIDs;
    }
}

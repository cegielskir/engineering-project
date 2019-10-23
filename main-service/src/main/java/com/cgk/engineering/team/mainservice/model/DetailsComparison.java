package com.cgk.engineering.team.mainservice.model;

import java.util.List;

public class DetailsComparison {
    private int id;

    private int percentage;
    private String firstArticleContent;
    private String secondArticleContent;
    private List<SuspiciousFragments> suspiciousWords;

    public DetailsComparison(int id, int percentage, String firstArticleContent, String secondArticleContent) {
        this.id = id;
        this.percentage = percentage;
        this.firstArticleContent = firstArticleContent;
        this.secondArticleContent = secondArticleContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getFirstArticleContent() {
        return firstArticleContent;
    }

    public void setFirstArticleContent(String firstArticleContent) {
        this.firstArticleContent = firstArticleContent;
    }

    public String getSecondArticleContent() {
        return secondArticleContent;
    }

    public void setSecondArticleContent(String secondArticleContent) {
        this.secondArticleContent = secondArticleContent;
    }

    public List<SuspiciousFragments> getSuspiciousWords() {
        return suspiciousWords;
    }

    public void setSuspiciousWords(List<SuspiciousFragments> suspiciousWords) {
        this.suspiciousWords = suspiciousWords;
    }
}

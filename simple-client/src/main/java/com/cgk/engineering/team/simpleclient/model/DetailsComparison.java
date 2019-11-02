package com.cgk.engineering.team.simpleclient.model;

import java.util.List;

public class DetailsComparison extends Comparison {
    private String firstArticleContent;
    private String secondArticleContent;
    private List<SuspiciousFragments> suspiciousWords;

    public DetailsComparison(int id, int percentage, String firstArticleContent, String secondArticleContent) {
        super(id, percentage);
        this.firstArticleContent = firstArticleContent;
        this.secondArticleContent = secondArticleContent;
    }

    public String getFirstArticleContent() {
        return firstArticleContent;
    }

    public void setFirstArticleContent(String firstArticleShortContent) {
        this.firstArticleContent = firstArticleShortContent;
    }

    public String getSecondArticleContent() {
        return secondArticleContent;
    }

    public void setSecondArticleContent(String secondArticleShortContent) {
        this.secondArticleContent = secondArticleShortContent;
    }

    public List<SuspiciousFragments> getSuspiciousWords() {
        return suspiciousWords;
    }

    public void setSuspiciousWords(List<SuspiciousFragments> suspiciousWords) {
        this.suspiciousWords = suspiciousWords;
    }
}

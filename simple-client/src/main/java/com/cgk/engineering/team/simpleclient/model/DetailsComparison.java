package com.cgk.engineering.team.simpleclient.model;

import java.util.List;

public class DetailsComparison implements IComparison {
    private int id;

    private String firstArticleContent;
    private String secondArticleContent;
    private List<SuspiciousFragments> suspiciousWords;

    public DetailsComparison(int id, String firstArticleContent, String secondArticleContent) {
        this.id = id;
        this.firstArticleContent = firstArticleContent;
        this.secondArticleContent = secondArticleContent;
    }

    public DetailsComparison() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

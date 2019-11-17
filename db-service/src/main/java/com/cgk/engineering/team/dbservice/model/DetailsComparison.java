package com.cgk.engineering.team.dbservice.model;

import org.bson.types.ObjectId;

import java.util.List;

public class DetailsComparison extends Comparison {
    private String firstArticleContent;
    private String secondArticleContent;
    private List<SuspiciousFragments> suspiciousWords;
    private int similarityPercentage;

    public DetailsComparison(ObjectId id, int percentage, int similarityPercentage, String firstArticleContent, String secondArticleContent) {
        super(id, percentage);
        this.firstArticleContent = firstArticleContent;
        this.secondArticleContent = secondArticleContent;
        this.similarityPercentage = similarityPercentage;
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

    public int getSimilarityPercentage() {
        return similarityPercentage;
    }

    public void setSimilarityPercentage(int similarityPercentage) {
        this.similarityPercentage = similarityPercentage;
    }
}

package com.cgk.engineering.team.simpleclient.model;

import java.util.List;

public class DetailedComparison extends AbstractComparison {
    private Article article1;
    private Article article2;
    private List<SuspiciousFragments> suspiciousWords;
    private int similarityPercentage;

    public DetailedComparison(int similarityPercentage, Article firstArticle, Article secondArticle) {
        this.article1 = firstArticle;
        this.article2 = secondArticle;
        this.similarityPercentage = similarityPercentage;
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

    public Article getArticle1() {
        return article1;
    }

    public void setArticle1(Article article1) {
        this.article1 = article1;
    }

    public Article getArticle2() {
        return article2;
    }

    public void setArticle2(Article article2) {
        this.article2 = article2;
    }
}
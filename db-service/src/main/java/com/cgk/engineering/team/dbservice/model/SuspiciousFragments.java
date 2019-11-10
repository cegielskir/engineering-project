package com.cgk.engineering.team.dbservice.model;

public class SuspiciousFragments {
    private int firstArticleStart, firstArticleEnd;
    private int secondArticleStart, secondArticleEnd;

    public SuspiciousFragments(int firstArticleStart, int firstArticleEnd, int secondArticleStart, int secondArticleEnd) {
        this.firstArticleStart = firstArticleStart;
        this.firstArticleEnd = firstArticleEnd;
        this.secondArticleStart = secondArticleStart;
        this.secondArticleEnd = secondArticleEnd;
    }

    public SuspiciousFragments() {
    }

    public int getFirstArticleStart() {
        return firstArticleStart;
    }

    public void setFirstArticleStart(int firstArticleStart) {
        this.firstArticleStart = firstArticleStart;
    }

    public int getFirstArticleEnd() {
        return firstArticleEnd;
    }

    public void setFirstArticleEnd(int firstArticleEnd) {
        this.firstArticleEnd = firstArticleEnd;
    }

    public int getSecondArticleStart() {
        return secondArticleStart;
    }

    public void setSecondArticleStart(int secondArticleStart) {
        this.secondArticleStart = secondArticleStart;
    }

    public int getSecondArticleEnd() {
        return secondArticleEnd;
    }

    public void setSecondArticleEnd(int secondArticleEnd) {
        this.secondArticleEnd = secondArticleEnd;
    }
}

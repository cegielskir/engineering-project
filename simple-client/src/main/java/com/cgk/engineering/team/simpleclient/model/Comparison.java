package com.cgk.engineering.team.simpleclient.model;

public class Comparison implements IComparison {
    private int id;

    private int percentage;
    private String firstArticleID;
    private String secondArticleID;
    private String firstArticleShortContent;
    private String secondArticleShortContent;

    public Comparison(int id) {
        this.id = id;
    }

    public Comparison() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPercentage() { return percentage; }

    public void setPercentage(int percentage) {
        if(percentage<=100 && percentage>=0)
            this.percentage = percentage;
        else throw new IllegalArgumentException();
    }

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

    public String getFirstArticleShortContent() {
        return firstArticleShortContent;
    }

    public void setFirstArticleShortContent(String firstArticleShortContent) {
        this.firstArticleShortContent = firstArticleShortContent;
    }

    public String getSecondArticleShortContent() {
        return secondArticleShortContent;
    }

    public void setSecondArticleShortContent(String secondArticleShortContent) {
        this.secondArticleShortContent = secondArticleShortContent;
    }
}

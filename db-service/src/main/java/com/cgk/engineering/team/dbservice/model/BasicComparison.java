package com.cgk.engineering.team.dbservice.model;

public class BasicComparison extends Comparison {
    private String firstArticleID;
    private String secondArticleID;
    private String firstArticleShortContent;
    private String secondArticleShortContent;

    public BasicComparison(int id) {
        super(id, 0);
    }

    public BasicComparison() {}

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


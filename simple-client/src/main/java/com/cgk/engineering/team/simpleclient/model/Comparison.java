package com.cgk.engineering.team.simpleclient.model;

import org.bson.types.ObjectId;
import org.omg.CORBA.Object;

public class Comparison {

    private int id;

    //include comparator type

    private int percentage;
    private int[] suspiciousWords1;
    private int[] suspiciousWords2;
    private String firstArticleID;
    private String secondArticleID;

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

    public void setSuspiciousWords(int[] suspiciousWords1, int[] suspiciousWords2){
        this.suspiciousWords1=suspiciousWords1;
        this.suspiciousWords2=suspiciousWords2;
    }

    public int[] getSuspiciousWords2() {
        return suspiciousWords2;
    }

    public int[] getSuspiciousWords1() {
        return suspiciousWords1;
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
}
package com.cgk.engineering.team.simpleclient.model;

import org.bson.types.ObjectId;

public class FastComparison {

    private ObjectId id1;
    private ObjectId id2;

    private String partOfContent1;
    private String partOfContent2;

    private double similarityPercentage;

    public ObjectId getId1() {
        return id1;
    }

    public void setId1(ObjectId id1) {
        this.id1 = id1;
    }

    public ObjectId getId2() {
        return id2;
    }

    public void setId2(ObjectId id2) {
        this.id2 = id2;
    }

    public String getPartOfContent1() {
        return partOfContent1;
    }

    public void setPartOfContent1(String partOfContent1) {
        this.partOfContent1 = partOfContent1;
    }

    public String getPartOfContent2() {
        return partOfContent2;
    }

    public void setPartOfContent2(String partOfContent2) {
        this.partOfContent2 = partOfContent2;
    }

    public double getSimilarityPercentage() {
        return similarityPercentage;
    }

    public void setSimilarityPercentage(double similarityPercentage) {
        this.similarityPercentage = similarityPercentage;
    }
}

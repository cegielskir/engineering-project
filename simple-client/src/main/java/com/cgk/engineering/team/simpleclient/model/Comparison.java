package com.cgk.engineering.team.simpleclient.model;

import org.bson.types.ObjectId;

public abstract class Comparison {
    private ObjectId id;
    private int percentage;

    public Comparison() {
    }

    public Comparison(ObjectId id, int percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        if(percentage<=100 && percentage>=0)
            this.percentage = percentage;
        else throw new IllegalArgumentException();
    }
}


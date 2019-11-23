package com.cgk.engineering.team.simpleclient.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class Comparison {
    @Id
    private String id;
    private int percentage;

    public Comparison() {
    }

    public Comparison(String id, int percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


package com.cgk.engineering.team.dbservice.model;

public abstract class Comparison {
    private int id;
    private int percentage;

    public Comparison() {
    }

    public Comparison(int id, int percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


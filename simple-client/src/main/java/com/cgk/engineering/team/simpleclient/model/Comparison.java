package com.cgk.engineering.team.simpleclient.model;

public class Comparison {

    private int id;

    //include comparator type

    private int percentage;
    private int[] theSameWords;

    public Comparison(int id) {
        this.id = id;

    }

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

    public int[] getTheSameWords() {
        return theSameWords;
    }

    public void setTheSameWords(int[] theSameWords) {
        this.theSameWords = theSameWords;
    }
}

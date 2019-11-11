package com.cgk.engineering.team.simpleclient.model;

public class ComparisonData {


    private Article article1;

    private Article article2;

    private String metric = "";

    private BasicComparison basicComparison;

    public ComparisonData() {
    }

    public ComparisonData(Article article1, Article article2) {
        this.article1 = article1;
        this.article2 = article2;
    }

    public ComparisonData(Article article1, Article article2, BasicComparison basicComparison) {
        this.article1 = article1;
        this.article2 = article2;
        this.basicComparison = basicComparison;
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

    public String getMetric() { return metric;}

    public void setMetric(String metric) {this.metric = metric;}

    public BasicComparison getBasicComparison() {
        return basicComparison;
    }

    public void setBasicComparison(BasicComparison basicComparison) {
        this.basicComparison = basicComparison;
    }
}

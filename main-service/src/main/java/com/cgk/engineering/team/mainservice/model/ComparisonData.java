package com.cgk.engineering.team.mainservice.model;


public class ComparisonData {


    private Article article1;

    private Article article2;

    public ComparisonData(Article article1, Article article2) {
        this.article1 = article1;
        this.article2 = article2;
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
}

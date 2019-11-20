package com.cgk.engineering.team.dbservice.model;

import java.util.ArrayList;
import java.util.List;

public class ComparisonData {


    private Article article1;

    private Article article2;

    private String metric;
    private List<String> allMetrics = new ArrayList<>();

    public ComparisonData() {
    }

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

    public List<String> getAllMetrics() {
        return allMetrics;
    }

    public void setAllMetrics(List<String> allMetrics) {
        this.allMetrics = allMetrics;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}

package com.cgk.engineering.team.mainservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComparisonData {

    private Article article1;
    private Article article2;

    private String metric;
    private Map<String, Boolean> alreadyInDbByMetricMap;

    private List<String> allMetrics = new ArrayList<>();

    public ComparisonData() {
    }

    public ComparisonData(Article article1, Article article2, Map<String, Boolean> alreadyInDbByMetricMap) {
        this.article1 = article1;
        this.article2 = article2;
        this.alreadyInDbByMetricMap = alreadyInDbByMetricMap;
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

    public Map<String, Boolean> getAlreadyInDbByMetricMap() {
        return alreadyInDbByMetricMap;
    }

    public void setAlreadyInDbByMetricMap(Map<String, Boolean> alreadyInDbByMetricMap) {
        this.alreadyInDbByMetricMap = alreadyInDbByMetricMap;
    }
}

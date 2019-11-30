package com.cgk.engineering.team.simpleclient.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComparisonData {

    private Article article1;
    private Article article2;

    private Map<String, Integer> comparisonMap;

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

    public void initComparisonMap(List<String> metrics){
        comparisonMap = new HashMap<>();
        for(String metric : metrics){
            comparisonMap.put(metric, -1);
        }
    }

    public void addComparison(String metric, int percentage){
        this.comparisonMap.put(metric, percentage);
    }

    public int getComparison(String metric){
        return this.comparisonMap.get(metric);
    }

    public Map<String, Integer> getComparisonMap() {
        return comparisonMap;
    }

    public void setComparisonMap(Map<String, Integer> comparisonMap) {
        this.comparisonMap = comparisonMap;
    }
}
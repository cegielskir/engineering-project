package com.cgk.engineering.team.dbservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.*;

public class ComparisonData {

    @Id
    private String id;

    @DBRef
    private Article article1;
    @DBRef
    private Article article2;

    @Indexed
    private Set<String> articleIDs;

    private Map<String, Integer> comparisonMap;

    public ComparisonData() {
    }

    public ComparisonData(Article article1, Article article2) {
        this.article1 = article1;
        this.article2 = article2;
        this.articleIDs = new HashSet<>(Arrays.asList(article1.getId(), article2.getId()));
        this.comparisonMap = new HashMap<>();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getArticleIDs() {
        return articleIDs;
    }

    public void setArticleIDs(Set<String> articleIDs) {
        this.articleIDs = articleIDs;
    }
}
package com.cgk.engineering.team.mainservice.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BasicComparison extends AbstractComparison {

    private Set<String> articleIDs;

    private Article secondArticle;

    public BasicComparison() {}

    public void setArticleIDs(String firstArticleID, String secondArticleID) {
        this.articleIDs = new HashSet<>(Arrays.asList(firstArticleID, secondArticleID));
    }

    public Set<String> getArticleIDs() {
        return articleIDs;
    }

    public void setArticleIDs(Set<String> articleIDs) {
        this.articleIDs = articleIDs;
    }

    public Article getSecondArticle() {
        return secondArticle;
    }

    public void setSecondArticle(Article secondArticle) {
        this.secondArticle = secondArticle;
    }
}

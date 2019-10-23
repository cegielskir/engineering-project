package com.cgk.engineering.team.simpleclient.model;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.simpleclient.algorithm.NormalizedLongestCommonSubstring;

import java.util.Random;

public class LCSComparator implements IComparator {
    private Article article1, article2;

    public LCSComparator(Article article1, Article article2){
        this.article1=article1;
        this.article2=article2;
    }

    public DetailsComparison compareArticles() {
        Random random = new Random();
        DetailsComparison detailsComparison =
                new DetailsComparison(random.nextInt(100000), article1.getContent(), article2.getContent());
        NormalizedLongestCommonSubstring nlcs = new NormalizedLongestCommonSubstring();
        detailsComparison.setSuspiciousWords(nlcs.similarity(
                article1.getContent(), article2.getContent()));

        return detailsComparison;
    }
}

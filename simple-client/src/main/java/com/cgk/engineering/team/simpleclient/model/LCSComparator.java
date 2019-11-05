package com.cgk.engineering.team.simpleclient.model;

import com.cgk.engineering.team.simpleclient.algorithm.NormalizedLongestCommonSubstring;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;

import java.util.Random;

public class LCSComparator implements IComparator {
    private Article article1, article2;

    public LCSComparator(Article article1, Article article2){
        this.article1=article1;
        this.article2=article2;
    }

    public DetailsComparison compareArticles() {
        System.out.println("details comparison");
        Random random = new Random();
        StringMetric stringMetric = StringMetrics.cosineSimilarity();
        float result = stringMetric.compare(article1.getContent(), article2.getContent());
        double percentage = 100*result;
        DetailsComparison detailsComparison =
                new DetailsComparison(random.nextInt(100000),
                        (int)percentage,
                        article1.getContent(),
                        article2.getContent());
        NormalizedLongestCommonSubstring nlcs = new NormalizedLongestCommonSubstring();
        detailsComparison.setSuspiciousWords(nlcs.similarity(
                article1.getContent(), article2.getContent()));

        return detailsComparison;
    }
}

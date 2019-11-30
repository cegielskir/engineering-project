package com.cgk.engineering.team.simpleclient.model;

import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;

public class SimmetricsComparator implements IComparator {
    private Article article1, article2;
    private String metric;

    public SimmetricsComparator(Article article1, Article article2, String metric){
        this.article1=article1;
        this.article2=article2;
        this.metric=metric;
    }

    @Override
    public BasicComparison compareArticles() {
        BasicComparison c = new BasicComparison();
        StringMetric stringMetric;
        c.setArticleIDs( article1.getId(),  article2.getId());
        switch (metric){
            case "CosineSimilarity":
                stringMetric = StringMetrics.cosineSimilarity();
                break;
            case "Dice":
                stringMetric = StringMetrics.dice();
                break;
            case "Jaccard":
                stringMetric = StringMetrics.jaccard();
                break;
            case "GeneralizedJaccard":
                stringMetric = StringMetrics.generalizedJaccard();
                break;
            case "BlockDistance":
                stringMetric = StringMetrics.blockDistance();
                break;
            case "OverlapCoefficient":
                stringMetric = StringMetrics.overlapCoefficient();
                break;
            case "Levenshtein":
                stringMetric = StringMetrics.levenshtein();
                break;
            default:
                stringMetric = StringMetrics.damerauLevenshtein();
        }

        float result = stringMetric.compare(article1.getContent(), article2.getContent());
        double percentage = 100*result;
        c.setPercentage((int)percentage);
        c.setMetric(metric);

        return c;
    }
}

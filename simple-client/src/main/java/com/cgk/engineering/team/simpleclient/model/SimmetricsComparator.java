package com.cgk.engineering.team.simpleclient.model;

import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;

import java.util.Random;

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
        Random random = new Random();
        BasicComparison c = new BasicComparison(random.nextInt(100000));
        StringMetric stringMetric;
        c.setArticleIDs( article1.get_id(),  article2.get_id());
        switch (metric){
            case "CosineSimilarity":
                stringMetric = StringMetrics.cosineSimilarity();
                break;
            case "BlockDistance":
                stringMetric = StringMetrics.blockDistance();
                break;
            case "DamerauLevenshtein":
                stringMetric = StringMetrics.damerauLevenshtein();
                break;
            case "Dice":
                stringMetric = StringMetrics.dice();
                break;
            default:
                stringMetric = StringMetrics.cosineSimilarity();
        }
        float result = stringMetric.compare(article1.getContent(), article2.getContent());
        double percentage = 100*result;
        c.setPercentage((int)percentage);
        c.setFirstArticleShortContent(article1.getContent().length() > 300
                ? article1.getContent().substring(0, 300) + "..."
                : article1.getContent());
        c.setSecondArticleShortContent(article2.getContent().length() > 300
                ? article2.getContent().substring(0, 300) + "..."
                : article2.getContent());

        return c;
    }
}

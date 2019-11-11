package com.cgk.engineering.team.simpleclient.model;

import com.cgk.engineering.team.mainservice.model.Article;
import com.cgk.engineering.team.mainservice.model.BasicComparison;
import org.bson.types.ObjectId;
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
        BasicComparison c = new BasicComparison(new ObjectId());
        StringMetric stringMetric;
        c.setArticleIDs( article1.get_id(),  article2.get_id());
        switch (metric){
            case "Dice":
                stringMetric = StringMetrics.dice();
                break;
            case "Jaccard":
                stringMetric = StringMetrics.jaccard();
                break;
            case "MongeElkan":
                stringMetric = StringMetrics.mongeElkan();
                break;
            case "JaroWinkler":
                stringMetric = StringMetrics.jaroWinkler();
                break;
            case "Levenshtein":
                stringMetric = StringMetrics.levenshtein();
                break;
            case "NeedlemanWunsch":
                stringMetric = StringMetrics.needlemanWunch();
                break;
            case "SmithWaterman":
                stringMetric = StringMetrics.smithWatermanGotoh();
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

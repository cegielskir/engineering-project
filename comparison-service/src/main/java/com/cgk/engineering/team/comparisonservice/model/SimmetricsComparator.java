package com.cgk.engineering.team.comparisonservice.model;

import com.cgk.engineering.team.simpleclient.algorithm.Ratcliffe;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;

import com.cgk.engineering.team.simpleclient.algorithm.Hamming;

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
        double result = 0.0;
        switch (metric){
            case "Hamming":
                stringMetric = null;
                result = new Hamming().compare(article1.getContent(), article2.getContent());
                break;
            case "Levenshtein":
                stringMetric = StringMetrics.levenshtein();
                break;
            case "JaroWinkler":
                stringMetric = StringMetrics.jaroWinkler();
                break;
            case "NeedlemanWunch":
                stringMetric = StringMetrics.needlemanWunch();
                break;
            case "SmithWaterman":
                stringMetric = StringMetrics.smithWatermanGotoh();
                break;
            case "BlockDistance":
                stringMetric = StringMetrics.blockDistance();
                break;
            case "CosineSimilarity":
                stringMetric = StringMetrics.cosineSimilarity();
                break;
            case "Dice":
                stringMetric = StringMetrics.dice();
                break;
            case "Euclidean":
                stringMetric = StringMetrics.euclideanDistance();
                break;
            case "Jaccard":
                stringMetric = StringMetrics.jaccard();
                break;
            case "OverlapCoefficient":
                stringMetric = StringMetrics.overlapCoefficient();
                break;
            case "LongestCommonSubsequence":
                stringMetric = StringMetrics.longestCommonSubsequence();
                break;
            case "LongestCommonSubstring":
                stringMetric = StringMetrics.longestCommonSubstring();
                break;
            case "RatcliffObershelp":
                stringMetric = null;
                result = new Ratcliffe().compare(article1.getContent(), article2.getContent());
                break;
            default:
                stringMetric = StringMetrics.damerauLevenshtein();
        }

        if(stringMetric != null) {
            result = stringMetric.compare(article1.getContent(), article2.getContent());
        }

        double percentage = 100*result;
        c.setPercentage((int)percentage);
        c.setMetric(metric);

        return c;
    }
}

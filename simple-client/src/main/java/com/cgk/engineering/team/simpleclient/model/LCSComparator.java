package com.cgk.engineering.team.simpleclient.model;

import com.cgk.engineering.team.simpleclient.algorithm.NormalizedLongestCommonSubstring;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Random;

public class LCSComparator implements IComparator {
    private Article article1, article2;

    public LCSComparator(Article article1, Article article2){
        this.article1=article1;
        this.article2=article2;
    }

    public DetailsComparison compareArticles() {
        NormalizedLongestCommonSubstring nlcs = new NormalizedLongestCommonSubstring();

        List<SuspiciousFragments> suspiciousFragmentsList = nlcs.similarity(article1.getContent(), article2.getContent());
        double plagiarismPercentage = 100*(nlcs.getPlagiarismChars()/
                article1.getContent().length());
        double similarityPercentage = 100*(nlcs.getSimilarChars()/
                article1.getContent().length());

        DetailsComparison detailsComparison =
                new DetailsComparison(
                        (int)similarityPercentage,
                        article1.getContent(),
                        article2.getContent());
        detailsComparison.setPercentage((int)plagiarismPercentage);
        detailsComparison.setSuspiciousWords(suspiciousFragmentsList);

        return detailsComparison;
    }
}

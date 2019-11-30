package com.cgk.engineering.team.simpleclient.model;

import com.cgk.engineering.team.simpleclient.algorithm.NormalizedLongestCommonSubstring;

import java.util.List;

public class LCSComparator implements IComparator {
    private Article article1, article2;

    public LCSComparator(Article article1, Article article2){
        this.article1=article1;
        this.article2=article2;
    }

    public DetailedComparison compareArticles() {
        NormalizedLongestCommonSubstring nlcs = new NormalizedLongestCommonSubstring();

        List<SuspiciousFragments> suspiciousFragmentsList = nlcs.similarity(article1.getContent(), article2.getContent());
        double plagiarismPercentage = 100*(nlcs.getPlagiarismChars()/
                article1.getContent().length());
        double similarityPercentage = 100*(nlcs.getSimilarChars()/
                article1.getContent().length());

        DetailedComparison detailedComparison =
                new DetailedComparison(
                        (int)similarityPercentage,
                        article1,
                        article2);
        detailedComparison.setPercentage((int)plagiarismPercentage);
        detailedComparison.setSuspiciousWords(suspiciousFragmentsList);

        return detailedComparison;
    }
}

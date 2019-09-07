package com.cgk.engineering.team.simpleclient.model;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.simpleclient.algorithm.NormalizedLongestCommonPhrase;

public class LCPComparator implements  IComparator {
    private Article article1, article2;

    public LCPComparator(Article article1, Article article2){
        this.article1=article1;
        this.article2=article2;
    }

    @Override
    public Comparison compareArticles(){

        Comparison c = new Comparison(18);
        NormalizedLongestCommonPhrase nlcp = new NormalizedLongestCommonPhrase();
        double percentage = 100* nlcp.similarity(article1.getContent(), article2.getContent());
        c.setPercentage((int) (percentage));
        c.setSuspiciousWords(markSuspiciousWords(nlcp.getIndexTo1(), nlcp.getSameWordsNum(), article1.getContent())
                , markSuspiciousWords(nlcp.getIndexTo2(), nlcp.getSameWordsNum(), article2.getContent()));
        return c;
    }

    private int[] markSuspiciousWords(int to, int length, String articleContent){
        String [] splitted = articleContent.split(" ");
        int[] suspWords = new int[splitted.length];
        for(int i=to-length+1;i<to;i++){
            suspWords[i]++;
        }
        return suspWords;
    }
}

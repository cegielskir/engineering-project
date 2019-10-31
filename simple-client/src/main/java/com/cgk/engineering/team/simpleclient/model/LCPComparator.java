package com.cgk.engineering.team.simpleclient.model;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.simpleclient.algorithm.NormalizedLongestCommonPhrase;

import java.util.Random;

public class LCPComparator implements  IComparator {
    private Article article1, article2;

    public LCPComparator(Article article1, Article article2){
        this.article1=article1;
        this.article2=article2;
    }

    @Override
    public Comparison compareArticles(){
        Random random = new Random();
        Comparison c = new Comparison();
        c.setArticleIDs( article1.get_id(),  article2.get_id());
        NormalizedLongestCommonPhrase nlcp = new NormalizedLongestCommonPhrase();
        double percentage = 100* nlcp.similarity(article1.getContent(), article2.getContent());
        c.setPercentage((int) (percentage));

        return c;
    }

    private int[] markSuspiciousWords(int to, int length, String articleContent){
        String [] splitted = articleContent.split(" ");
        int[] suspWords = new int[splitted.length];
        for(int i=to-length+1;i<=to;i++){
            suspWords[i]++;
        }
        return suspWords;
    }
}

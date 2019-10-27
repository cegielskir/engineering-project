package com.cgk.engineering.team.simpleclient.model;

import com.cgk.engineering.team.simpleclient.algorithm.NormalizedLongestCommonPhrase;
import org.bson.types.ObjectId;

import java.util.Random;

public class LCPComparator implements  IComparator {
    private Article article1, article2;

    public LCPComparator(Article article1, Article article2){
        this.article1=article1;
        this.article2=article2;
    }

    public FastComparison fastCompareArticles(){
        Random random = new Random();
        Comparison c = new Comparison(random.nextInt(100000));
        c.setArticleIDs( article1.get_id(),  article2.get_id());
        NormalizedLongestCommonPhrase nlcp = new NormalizedLongestCommonPhrase();
        double percentage = 100* nlcp.similarity(article1.getContent(), article2.getContent());

        FastComparison fastComparison = new FastComparison();
        fastComparison.setId1(new ObjectId(article1.get_id()));
        fastComparison.setId2(new ObjectId(article2.get_id()));
        fastComparison.setPartOfContent1(article1.getContent());
        fastComparison.setPartOfContent2(article2.getContent());
        fastComparison.setSimilarityPercentage(percentage);

        return fastComparison;
    }

    @Override
    public Comparison compareArticles(){
        Random random = new Random();
        Comparison c = new Comparison(random.nextInt(100000));
        c.setArticleIDs( article1.get_id(),  article2.get_id());
        NormalizedLongestCommonPhrase nlcp = new NormalizedLongestCommonPhrase();
        double percentage = 100* nlcp.similarity(article1.getContent(), article2.getContent());
        c.setPercentage((int) (percentage));
        c.setSuspiciousWords(markSuspiciousWords(nlcp.getIndexTo1(), nlcp.getSameWordsNum(), article1.getContent())
                , markSuspiciousWords(nlcp.getIndexTo2(), nlcp.getSameWordsNum(), article2.getContent()));
        c.setFirstArticleShortContent(article1.getContent());
        c.setSecondArticleShortContent(article2.getContent());
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

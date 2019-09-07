package com.cgk.engineering.team.simpleclient.model;

import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.simpleclient.algorithm.NormalizedLevenshtein;

public class LevenshteinComparator implements IComparator {

    private Article article1, article2;


    public LevenshteinComparator(Article article1, Article article2){
        //figure out how to prevent comparing pairs (article1, article2) and (article2, article1) as different
        this.article1=article1;
        this.article2=article2;
    }

    @Override
    public Comparison compareArticles() {
        Comparison comparison = new Comparison(17);

        NormalizedLevenshtein normalizedLevenshtein = new NormalizedLevenshtein();

        comparison.setPercentage((int) (100*normalizedLevenshtein.similarity(article1.getContent(), article2.getContent())));
        comparison.setTheSameWords(naiveWordsMarkup());

        return comparison;
    }

    private int[] naiveWordsMarkup() {
        String[] content1 = article1.getContent().split(" ");
        String[] content2 = article2.getContent().split(" ");

        int minWords = Math.min(content1.length, content2.length);
        int maxWords = Math.max(content1.length, content2.length);

        int[] sameWords = new int[minWords];
        for(int i=0;i<minWords;i++){
            if(content1[i].equals(content2[i])) sameWords[i]++;
        }

        return sameWords;
    }

}

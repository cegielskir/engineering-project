package com.cgk.engineering.team.simpleclient.model;

import com.cgk.engineering.team.simpleclient.algorithms.NormalizedLevenshtein;

public class SimpleComparator implements IComparator {

    private Article article1, article2;

    private static final int TITLEMAXSCORE = 10,
            CONTENTEQUALITYMAXSCORE=90;


    public SimpleComparator(Article article1, Article article2){
        //figure out how to prevent comparing pairs (article1, article2) and (article2, article1) as different
        this.article1=article1;
        this.article2=article2;
    }

    @Override
    public Comparison compareArticles() {
        Comparison comparison = new Comparison(17);

        NormalizedLevenshtein normalizedLevenshtein = new NormalizedLevenshtein();

        comparison.setPercentage((int) (100*normalizedLevenshtein.similarity(article1.getContent(), article2.getContent())));
        //comparison.setPercentage(compareTitle() + compareContentEquality());
        comparison.setTheSameWords(naiveWordsMarkup());

        return comparison;
    }


    private int compareTitle(){
        if(article1.getTitle().equals(article2.getTitle())) return TITLEMAXSCORE;
        else return 0;
    }

    private int compareContentEquality() {
        String[] content1 = article1.getContent().split(" ");
        String[] content2 = article2.getContent().split(" ");

        double sameWords=0;
        int minWords = Math.min(content1.length, content2.length);
        int maxWords = Math.max(content1.length, content2.length);
        for(int i=0;i<minWords;i++){
            if(content1[i].equals(content2[i])) sameWords++;
        }
        double sameWordsPart = sameWords/maxWords;

        return (int)Math.floor(sameWordsPart*CONTENTEQUALITYMAXSCORE);
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

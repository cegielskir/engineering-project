package com.cgk.engineering.team.simpleclient.algorithm;

import com.cgk.engineering.team.simpleclient.algorithm.interfaces.NormalizedStringSimilarity;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class NormalizedLongestCommonPhrase implements NormalizedStringSimilarity {
    private final LongestCommonPhrase lcp = new LongestCommonPhrase();
    private int indexTo1 =0, indexTo2 =0;
    private int sameWordsNum;
    Logger logger = LogManager.getLogger(this.getClass());
    public final double similarity(final String s1, final String s2) {

        if (s1 == null) {
            throw new NullPointerException("s1 must not be null");
        }

        if (s2 == null) {
            throw new NullPointerException("s2 must not be null");
        }

        if (s1.equals(s2)) {
            return 1;
        }

        int m_len = Math.max(s1.split(" ").length, s2.split(" ").length);

        if (m_len == 0) {
            return 1;
        }

        this.sameWordsNum=lcp.lcp(s1, s2);
        this.indexTo1 =lcp.getIndexTo1();
        this.indexTo2 =lcp.getIndexTo2();
        return (double) this.sameWordsNum/m_len;
    }
    public int getIndexTo1(){
        return indexTo1;
    }

    public int getIndexTo2(){
        return indexTo2;
    }
    public int getSameWordsNum(){
        return sameWordsNum;
    }
}

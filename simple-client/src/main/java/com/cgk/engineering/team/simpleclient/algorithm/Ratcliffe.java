package com.cgk.engineering.team.simpleclient.algorithm;

public class Ratcliffe {
    public double compare(String s1, String s2) {
        if(s1.equals(s2)) return 1.0;
        else if(s1.length() == 0 || s2.length() == 0) return 0.0;

        return ((double)similarity(s1.toUpperCase().toCharArray(), 0, s1.length(),
                s2.toUpperCase().toCharArray(), 0, s2.length())*2)/(s1.length()+s2.length());
    }

    private int similarity(char[] s1, int start1, int end1,
                           char[] s2, int start2, int end2) {
        int new_start1 = 0, new_start2 = 0, score = 0;

        if (start1 >= end1 || start2 >= end2 || start1 < 0 || start2 < 0)
            return score;

        for(int pos1=start1; pos1<end1; pos1++) {
            for(int pos2=start2; pos2<end2; pos2++) {
                int k = 0;
                while(s1[pos1+k] == s2[pos2+k]) {
                    k++;
                    if(k > score) {
                        new_start1 = pos1;
                        new_start2 = pos2;
                        score = k;
                    }
                    if ((pos1 + k) >= end1 || (pos2 + k) >= end2) break;
                }
            }
        }

        if(score != 0) {
            score += similarity(s1, new_start1+score, end1,
                    s2, new_start2 + score, end2);
            score += similarity(s1, start1, new_start1-1,
                    s2, start2, new_start2-1);
        }

        return score;
    }
}


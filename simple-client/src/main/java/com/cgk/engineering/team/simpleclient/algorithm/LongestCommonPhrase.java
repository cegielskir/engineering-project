package com.cgk.engineering.team.simpleclient.algorithm;

public class LongestCommonPhrase {

    private int indexTo1 = -2, indexTo2=-2;

    public final int lcp(final String s1, final String s2) {
        if (s1 == null) {
            throw new NullPointerException("s1 must not be null");
        }

        if (s2 == null) {
            throw new NullPointerException("s2 must not be null");
        }

        if (s1.equals(s2)) {
            return 0;
        }

        if (s1.length() == 0) {
            return s2.length();
        }

        if (s2.length() == 0) {
            return s1.length();
        }


        String[] words1 = s1.split(" ");
        String[] words2 = s2.split(" ");
        int length1 = words1.length;
        int length2 = words2.length;
        int[] v0 = new int[length2];
        int[] v1 = new int[length2];
        int z=0;
        int to1=0;
        int to2=0;

        for (int i=0;i<length1;i++){
            for (int j=0;j<length2;j++){

                if(words1[i].equals(words2[j])){
                    if(i==0 || j==0){
                        v1[j]=1;
                    } else{
                        v1[j] = v0[j-1]+1;
                    }
                } else{
                    v1[j]=0;
                }
                if(v1[j]>z){
                    z=v1[j];
                    to1 = i;
                    to2 = j;
                }
            }
            int[] swap = v0;v0=v1;v1=swap;
        }
        this.indexTo1 = to1;
        this.indexTo2 = to2;
        return z;
    }

    public int getIndexTo1(){
        return indexTo1;
    }
    public int getIndexTo2(){
        return indexTo2;
    }
}

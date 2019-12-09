package com.cgk.engineering.team.comparisonservice.algorithm;

import com.cgk.engineering.team.comparisonservice.model.SuspiciousFragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalizedLongestCommonSubstring {
    private double similarChars = 0;
    private double plagiarismChars = 0;

    public List<SuspiciousFragments> similarity(String s1, String s2) {
        LongestCommonSubstring lcs = new LongestCommonSubstring();
        List<SuspiciousFragments> similarityList = new ArrayList<>();
        int length;

        while(s1.length() > 10 && s2.length() > 10) {
            length = lcs.lcs(s1.toLowerCase().toCharArray(),
                             s2.toLowerCase().toCharArray(),
                             s1.length(), s2.length());
            int start1 = lcs.getEndIndex1() - length, end1 = lcs.getEndIndex1();
            int start2 = lcs.getEndIndex2() - length, end2 = lcs.getEndIndex2();
            if(length > 8) {
                if(length > 20) {
                    plagiarismChars += length;
                } else {
                    similarChars += length;
                }
                SuspiciousFragments suspiciousFragments =
                        new SuspiciousFragments(start1, end1, start2, end2);
                similarityList.add(suspiciousFragments);

                s1 = s1.replace(s1.substring(start1, end1), String.join("", Collections.nCopies(end1-start1, "$")));
                s2 = s2.replace(s2.substring(start2, end2), String.join("", Collections.nCopies(end1-start1, "$")));
            } else {
                return similarityList;
            }
        }
        return similarityList;
    }

    public double getSimilarChars() {
        return similarChars;
    }

    public double getPlagiarismChars() {
        return plagiarismChars;
    }
}

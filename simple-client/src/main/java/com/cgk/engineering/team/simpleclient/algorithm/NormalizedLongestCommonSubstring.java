package com.cgk.engineering.team.simpleclient.algorithm;

import com.cgk.engineering.team.simpleclient.model.SuspiciousFragments;

import java.util.ArrayList;
import java.util.List;

public class NormalizedLongestCommonSubstring {

    public List<SuspiciousFragments> similarity(String s1, String s2) {
        LongestCommonSubstring lcs = new LongestCommonSubstring();
        List<SuspiciousFragments> similarityList = new ArrayList<>();
        int length;

        do {
            length = lcs.lcs(s1.toCharArray(), s2.toCharArray(),
                             s1.length(), s2.length());
            int start1 = lcs.getEndIndex1() - length, end1 = lcs.getEndIndex1();
            int start2 = lcs.getEndIndex2() - length, end2 = lcs.getEndIndex2();

            SuspiciousFragments suspiciousFragments =
                    new SuspiciousFragments(start1, end1, start2, end2);
            similarityList.add(suspiciousFragments);
//            System.out.println(s1.length() + " " + s2.length());
//            System.out.println(length + " " + start1 + " " + start2 + " " + end1 + " " + end2);
//            System.out.println(s1.substring(start1, end1));
//            System.out.println(s2.substring(start2, end2));

            s1 = s1.replace(s1.substring(start1, end1), "");
            s2 = s2.replace(s2.substring(start2, end2), "");

        } while(length > 10 && s1.length() > 10 && s2.length() > 10);
        return similarityList;
    }

}

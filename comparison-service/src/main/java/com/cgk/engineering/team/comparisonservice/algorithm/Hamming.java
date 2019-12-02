package com.cgk.engineering.team.comparisonservice.algorithm;

import java.util.Collections;

public class Hamming {
    public double compare(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();

        if(len1 > len2) s2 = s2 + String.join("",  Collections.nCopies(len1-len2, " "));
        else if (len2 > len1) s1 = s1 + String.join("",  Collections.nCopies(len2-len1, " "));

        return 1.0 - ((double)(distance(s1, s2))/(len1));
    }

    private int distance(String str1, String str2)
    {
        int i = 0, count = 0;
        while (i < str1.length())
        {
            if (str1.charAt(i) != str2.charAt(i))
                count++;
            i++;
        }
        return count;
    }
}

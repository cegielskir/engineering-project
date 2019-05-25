package com.cgk.engineering.team.simpleclient.algorithms;

import com.cgk.engineering.team.simpleclient.algorithms.interfaces.NormalizedStringDistance;
import com.cgk.engineering.team.simpleclient.algorithms.interfaces.NormalizedStringSimilarity;

public class NormalizedLevenshtein implements
        NormalizedStringDistance, NormalizedStringSimilarity {

    private final Levenshtein l = new Levenshtein();

    public final double distance(final String s1, final String s2) {

        if (s1 == null) {
            throw new NullPointerException("s1 must not be null");
        }

        if (s2 == null) {
            throw new NullPointerException("s2 must not be null");
        }

        if (s1.equals(s2)) {
            return 0;
        }

        int m_len = Math.max(s1.length(), s2.length());

        if (m_len == 0) {
            return 0;
        }

        return l.distance(s1, s2) / m_len;
    }

    public final double similarity(final String s1, final String s2) {
        return 1.0 - distance(s1, s2);
    }

}
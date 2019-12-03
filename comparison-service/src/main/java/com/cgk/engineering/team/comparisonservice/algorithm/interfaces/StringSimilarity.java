package com.cgk.engineering.team.comparisonservice.algorithm.interfaces;

import java.io.Serializable;

public interface StringSimilarity extends Serializable {
    double similarity(String s1, String s2);
}


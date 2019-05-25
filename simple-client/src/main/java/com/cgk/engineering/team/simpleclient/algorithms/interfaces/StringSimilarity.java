package com.cgk.engineering.team.simpleclient.algorithms.interfaces;

import java.io.Serializable;

public interface StringSimilarity extends Serializable {
    double similarity(String s1, String s2);
}


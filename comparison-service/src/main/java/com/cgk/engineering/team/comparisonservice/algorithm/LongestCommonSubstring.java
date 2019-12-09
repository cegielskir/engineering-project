package com.cgk.engineering.team.comparisonservice.algorithm;

public class LongestCommonSubstring {
    private int endIndex1 = 0;
    private int endIndex2 = 0;

    public int getEndIndex1() {
        return endIndex1;
    }

    public int getEndIndex2() {
        return endIndex2;
    }

    public int lcs(char[] X, char[] Y, int m, int n) {
        int[][] LCStuff = new int[m + 1][n + 1];
        int result = 0;

        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                if (i == 0 || j == 0)
                    LCStuff[i][j] = 0;
                else if (X[i - 1] == Y[j - 1] &&
                        (X[i - 1] != '$' && Y[j - 1] != '$'))
                {
                    LCStuff[i][j] = LCStuff[i - 1][j - 1] + 1;
                    if(result < LCStuff[i][j]) {
                        endIndex1 = i; endIndex2 = j;
                        result = Integer.max(result, LCStuff[i][j]);
                    }
                }
                else
                    LCStuff[i][j] = 0;
            }
        }

        return result;
    }
}

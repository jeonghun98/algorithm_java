package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Baekjoon Online Judge 9252 LCS(Longest Common Subsequence, 최장 공통 부분 수열) 2
public class bj9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char st1[] = br.readLine().toCharArray();
        char st2[] = br.readLine().toCharArray();

        int len1 = st1.length;
        int len2 = st2.length;

        int dp[][] = new int[len2+1][len1+1];
        int LSC_len = 0;
        for(int i = 1; i < len2+1; i++) {
            for(int j = 1; j < len1+1; j++) {
                if(st2[i-1] == st1[j-1]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }

                if(LSC_len < dp[i][j]) LSC_len = dp[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(LSC_len + "\n");

        int x = len2, y = len1;
        char result[] = new char[LSC_len];
        while(x > 0 && y > 0) {
            if(dp[x][y] == dp[x-1][y]){
                x--;
            }else if(dp[x][y]==dp[x][y-1]) {
                y--;
            }else {
                result[--LSC_len] = st1[y - 1];
                x--; y--;
            }
        }

        for(int i = 0; i < result.length; i++) {
            sb.append(result[i]);
        }

        System.out.println(sb.toString());
    }
}

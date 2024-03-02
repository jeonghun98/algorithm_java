package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Baekjoon Online Judge 9251 LCS
public class bj9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] data1 = br.readLine().toCharArray();
        char[] data2 = br.readLine().toCharArray();

        int dp[][] = new int[data1.length + 1][data2.length + 1];
        for(int i = 1; i < data1.length + 1; i++) {
            for(int j = 1; j < data2.length + 1; j++) {
                if(data1[i-1] == data2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[data1.length][data2.length]);
    }
}

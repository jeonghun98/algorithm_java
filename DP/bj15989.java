package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// Baekjoon Online Judge 15989 1, 2, 3 더하기 4
public class bj15989 {
    public static int dp(int num) {
        int[] dp = new int[10001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int i = 4; i <= num; i++) {
            dp[i] = 1 + i/2 + dp[i-3];
        }
//        Arrays.fill(dp,1);
//        for(int i = 2; i <= num; i++) dp[i] += dp[i-2];
//        for(int i = 3; i <= num; i++) dp[i] += dp[i-3];

        return dp[num];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            System.out.println(dp(Integer.parseInt(br.readLine())));
        }
    }
}

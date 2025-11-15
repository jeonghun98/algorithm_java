package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Baekjoon Online Judge 9095 1, 2, 3 더하기
public class bj9095 {
    public static int dp(int n) {
        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        if(n > 3) {
            for(int i = 4; i <= n; i++) dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        return dp[n];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            System.out.println(dp(Integer.parseInt(br.readLine())));
        }
    }
}

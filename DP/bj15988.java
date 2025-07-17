package algorithm_java.DP;

import java.io.*;

// Baekjoon Online Judge 15988 1, 2, 3 더하기 3
public class bj15988 {
    static final int MOD = 1_000_000_009;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        long[] dp = new long[1_000_001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int j = 4; j <= 1_000_000; j++) {
            dp[j] = (((dp[j-1] + dp[j-2]) % MOD) + dp[j-3]) % MOD;
        }

        for(int i = 1; i <= t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}

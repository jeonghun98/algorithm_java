package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Baekjoon Online Judge 12865 평범한 배낭
public class bj12865_v2025 {
    public static int knapsack(int n, int k, int[][] dp, int[] weight, int[] value) {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= k; j++) {
                dp[i][j] = dp[i-1][j];
                if(weight[i] <= j) dp[i][j] = Math.max(dp[i][j], value[i]+dp[i-1][j-weight[i]]);
            }
        }
        return dp[n][k];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            for(int j = k; j >= w; j--) {
                dp[j] = Math.max(dp[j], dp[j-w] + v);
            }
        }
        System.out.println(dp[k]);

        int[][] dp2 = new int[n+1][k+1];
        int[] weight = new int[n+1];
        int[] value = new int[n+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(knapsack(n, k, dp2, weight, value));
    }
}

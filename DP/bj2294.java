package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 2294 동전 2
public class bj2294 {
    static final int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int dp[] = new int[k+1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for(int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            for(int j = coin; j < k+1; j++) {
                if(dp[j-coin] != MAX) {
                    dp[j] = Math.min(dp[j], dp[j-coin] + 1);
                }
            }
        }

        System.out.println(dp[k] == MAX ? -1 : dp[k]);
    }
}

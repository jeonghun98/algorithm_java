package algorithm_java.BOJ_SWEA_0420;

import java.util.Scanner;

// Baekjoon Online Judge 1562 계단 수
public class bj1562 {
    static long mod = 1_000_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(stairs(n));
    }
    public static long stairs(int n) {
        long dp[][][] = new long[n+1][11][1<<10];
        for(int i = 1; i < 10; i++) {
            dp[1][i][1<<i] = 1;
        }

        for(int i = 2; i < n+1; i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k < (1<<10); k++) {
                    int bit = k | (1<<j);
                    if(j == 0)
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k]) % mod;
                    else if (j == 9)
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][k]) % mod;
                    else
                        dp[i][j][bit] = (dp[i][j][bit]  + dp[i-1][j+1][k] + dp[i-1][j-1][k]) % mod;
                }
            }
        }

        long sum = 0;
        for(int i=0; i < 10; i++)
            sum = (sum + dp[n][i][(1<<10) - 1]) % mod;

        return sum;
    }
}
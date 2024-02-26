package algorithm_java.TSP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Baekjoon Online Judge 17182 우주 탐사선
public class bj17182 {
    static int n, k;
    static int data[][];
    static int dp[][];
    static int result;
    static final int INF = Integer.MAX_VALUE;
    public static void dfs(int visited, int pre, int time) {
        if(visited == (1<<n)-1) {
            result = Math.min(result, time);
            return;
        }
        for(int i = 0; i < n; i++) {
            if((visited & (1<<i)) != 0) continue;
            dfs(visited | (1<<i), i, time + data[pre][i]);
        }
    }
    public static int tsp(int visited, int city) {
        if(visited == (1<<n)-1) {
            return dp[visited][k] = 0;
        }

        if(dp[visited][city] != 0) {
            return dp[visited][city];
        }

        dp[visited][city] = INF;
        for(int i = 0; i < n; i++) {
            if((visited & (1<<i)) == 0) {
                int res = tsp(visited | (1<<i), i) + data[city][i];
                dp[visited][city] = Math.min(res, dp[visited][city]);
            }
        }
        return dp[visited][city];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        data = new int[n][n];

        result = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(data[i][j] > data[i][k] + data[k][j]) {
                        data[i][j] = data[i][k] + data[k][j];
                    }
                }
            }
        }
//        dfs(1<<k, k, 0);
//        System.out.println(result);
        dp = new int[(1<<n)][n];
        System.out.println(tsp(1<<k, k));
    }
}
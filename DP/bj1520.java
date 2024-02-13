package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1520 내리막 길
public class bj1520 {
    static int n, m;
    static int data[][];
    static int dp[][];
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,-1,0,1};
    public static int dfs(int x, int y) {
        if(x == n-1 && y == m-1) return 1;
        if(dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m || data[x][y] <= data[nx][ny]) continue;
            dp[x][y] += dfs(nx, ny);
        }
        return dp[x][y];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data  = new int[n][m];
        dp = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for(int j = 0; j < m; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0,0));
    }
}

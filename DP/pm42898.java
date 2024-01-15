package algorithm_java.DP;

// programmers 42898 등굣길
public class pm42898 {
    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int dp[][] = new int[n][m];
        dp[0][0] = 1;

        for(int[] puddle : puddles) {
            dp[puddle[1]-1][puddle[0]-1] = -1;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }

                // dp[i][j] = dp[i][j-1] + dp[i-1][j] => 항상 왼쪽과 위에서 온다 = 최단은 오른쪽과 아래로 향한다
                if(i != 0) dp[i][j] += dp[i-1][j] % 1000000007;
                if(j != 0) dp[i][j] += dp[i][j-1] % 1000000007;
            }
        }
        return dp[n-1][m-1] % 1000000007;
    }

    public static void main(String[] args) {
        System.out.println(solution(4,3, new int[][] {{2,2}}));
    }
}

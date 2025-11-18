package algorithm_java.DP;

// programmers 389480 완전범죄
class pm389480 {
    public static int solution(int[][] info, int n, int m) {
        int answer = n;
        int len = info.length;
        int[] dp = new int[m];

        for(int i = 0; i < len; i++) {
            int a = info[i][0];
            int b = info[i][1];
            for(int j = m-1; j >= 0; j--) {
                if(j < b) dp[j] += a;
                else dp[j] = Math.min(dp[j-b], dp[j]+a);
            }
        }

//         int answer = n;
//         int len = info.length;
//         int[][] dp = new int[len+1][m];
//         for(int i = 1; i < infoLen+1; i++) {
//             int a = info[i-1][0];
//             int b = info[i-1][1];
//             for(int j = 0; j < m; j++) {
//                 if(j < b) {
//                     dp[i][j] = dp[i-1][j] + a;
//                     continue;
//                 }
//                 dp[i][j] = Math.min(dp[i-1][j-b], dp[i-1][j]+a);
//             }
//         }
//         for(int i = 0; i < m; i++) {
//             answer = Math.min(answer, dp[len][i]);
//         }

        for(int i = 0; i < m; i++) {
            answer = Math.min(answer, dp[i]);
        }

        return answer >= n ? -1 : answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{1,2}, {2,3}, {2,1}}, 4, 4));
        System.out.println(solution(new int[][] {{1,2}, {2,3}, {2,1}}, 1, 7));
        System.out.println(solution(new int[][] {{3,3}, {3,3}}, 7, 1));
        System.out.println(solution(new int[][] {{3,3}, {3,3}}, 6, 1));
    }
}
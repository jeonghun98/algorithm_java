package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Baekjoon Online Judge 12101 1, 2, 3 더하기 2
public class bj12101 {
    private static int n, k, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp2(0, "");
        if(cnt < k) System.out.println(-1);

//        int[] dp = new int[11];
//        dp[0] = dp[1] = 1; dp[2] = 2; dp[3] = 4;
//        for(int i = 4; i <= n; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
//        }
//
//        if(k > dp[n]) {
//            System.out.println(-1);
//            return;
//        }
//
//        while(true) {
//            if(k <= dp[n-1]) {
//                System.out.print(1);
//                n -= 1;
//            } else if(k <= dp[n-1] + dp[n-2]) {
//                System.out.print(2);
//                k -= dp[n-1];
//                n -= 2;
//            } else {
//                System.out.print(3);
//                k -= (dp[n-1] + dp[n-2]);
//                n -= 3;
//            }
//            if(n < 1) break;
//            System.out.print("+");
//        }
    }
    private static void dp2(int number, String str) {
        if(number > n) return;

        if(number == n) {
            cnt++;
            if(cnt == k) {
                System.out.println(str.substring(1));
                return;
            }
        }
        dp2(number + 1, str + "+1");
        dp2(number + 2, str + "+2");
        dp2(number + 3, str + "+3");
    }
}

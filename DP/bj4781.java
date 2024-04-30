package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Baekjoon Online Judge 4781 사탕가게
public class bj4781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n, money;
        int dp[][], candy[][];
        int dp2[];

        while(true) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            money = (int) (Float.parseFloat(st.nextToken()) * 100 + 0.5);
            if(n == 0 && money == 0) break;

//            candy = new int[n+1][2];
//            dp = new int[n+1][money+1];
            dp2 = new int[money+1];

            for(int i = 1; i < n+1; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int p = (int)(Float.parseFloat(st.nextToken())*100 + 0.5);
//                candy[i][0] = c;
//                candy[i][1] = p;

                // 배낭문제에서 1차원 dp는 뒤에서 접근하나 해당 문제는 여러번 사용가능하므로 앞에서 접근
                for(int j = p; j < money+1; j++){
                    dp2[j] = Math.max(dp2[j], dp2[j-p]+c);
                }
            }

//            for(int i = 1; i < n+1; i++) {
//                for(int j = 1; j < money+1; j++) {
//                    if(j < candy[i][1]) {
//                        dp[i][j] = dp[i-1][j];
//                    } else {
//                        int idx = j-candy[i][1];
//                        int max = Math.max(dp[i-1][j], candy[i][0] + dp[i-1][idx]);
//                        dp[i][j] = Math.max(max, candy[i][0] + dp[i][idx]);
//                    }
//                }
//            }

            sb.append(dp2[money]+"\n");

        }
        System.out.println(sb);
    }
}

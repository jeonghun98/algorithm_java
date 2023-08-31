package algorithm_java.DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// Baekjoon Online Judge 2293 동전 1
public class bj2293 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int array[] = new int[n];
        for(int i = 0; i < n; i++)
            array[i] = Integer.parseInt(br.readLine()); // 동전 배열

        int max = Integer.MAX_VALUE;
        int dp[] = new int[k+1];
        Arrays.fill(dp, max); // max로 채우기 (k숫자까지)
        dp[0] = 0;

        for(int i = 0; i < n; i++) {
            for(int j = array[i]; j <= k; j++) {
                if(dp[j-array[i]] == 0) { // 해당 동전의 시작이라면
                    if(dp[j] != max) dp[j] = dp[j] + 1; // dp에 값이 갱신되어 있었다면 +1
                    else dp[j] = 1;						// dp에 값이 갱신되어 있지 않았으면 1만 넣기
                }

                else if(dp[j-array[i]] != max) { // 현재 동전으로도 만들 수 있는 숫자라면
                    if(dp[j] != max)	// 현재 값이 max가 아니라면
                        dp[j] += dp[j-array[i]];	// 현재 값 += 전 값
                    else
                        dp[j] = dp[j-array[i]];		// 현재 값이 max라면 전 값 그대로 갱신
                }
            }
        }

        System.out.println(dp[k] == max ? 0 : dp[k]); // max값이 갱신되지 않았다면 경우의 수 -> 0으로 바꿈
    }
}

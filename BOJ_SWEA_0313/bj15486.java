package algorithm_java.BOJ_SWEA_0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj15486 {

	static int n;
	static int retire[][];
	static long dp[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		retire = new int[n+1][2];
		dp = new long[n+2];
		for(int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine());
			retire[i][0] = Integer.parseInt(st.nextToken());
			retire[i][1] = Integer.parseInt(st.nextToken());
		}
		find_max();
	}
	
	public static void find_max() {
		for(int i = n; i > 0; i--) {
			dp[i] = dp[i+1]; // 갱신
			
			if(retire[i][0] + i > n + 1) continue; // 상담 날짜 넘어가면 continue
			
			dp[i] = Math.max(retire[i][1] + dp[retire[i][0] + i] , dp[i]); // 상담 가능 -> 최댓값으로 갱신
		}
		System.out.println(dp[1]);
	}
}

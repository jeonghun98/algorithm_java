package algorithm_java.BOJ_SWEA_0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1010 {
	static int n, m;
	
	public static long brige2() { // 파스칼 -> combination
		int[][] dp2 = new int[m+1][n+1];
		
		for(int i = 0; i <= m; i++) {
			for(int j = 0, end = Math.min(i, n); j <= end;j++) {
				if(j == 0 || i == j) dp2[i][j] = 1;
				else dp2[i][j] = dp2[i-1][j-1] + dp2[i-1][j];
			}
		}
		
		return dp2[m][n];
	}
	
	
	public static long brige() {  // factorial -> combination
		if(n == 1) return m;
		if(n == m) return 1;
		
		double dp[] = new double[m+1];
		for(int i = 1; i < m+1; i++) {
			if(i == 1) dp[i] = 1;
			else dp[i] = dp[i-1]*i; // factorial
		}
		
		return Math.round(dp[m] / (dp[m-n] * dp[n])); // combination
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			System.out.println(brige2());
		}
	}

}

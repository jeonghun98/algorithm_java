package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 1149 RGB거리
public class bj1149 {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int dp[][] = new int[n+1][3];
		
		for(int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
			
				dp[i][j] = Integer.parseInt(st.nextToken()); //현재 집의 비용 넣기
				
				if(i == 1) continue; //1번 집은 전 집과 비교하지 x 
				
				int min = Integer.MAX_VALUE;
				// 자신을 제외한 색을 가진 전 집의 최소 비용 찾기
				
				if(j == 0) min = Math.min(dp[i-1][1], dp[i-1][2]);
				else if(j == 1) min = Math.min(dp[i-1][0], dp[i-1][2]);
				else min = Math.min(dp[i-1][0], dp[i-1][1]);
				
				dp[i][j] += min; // 현재 집의 비용에 전 집의 최소비용 더하기
			}
		}
		
		int result = Math.min(dp[n][0], dp[n][1]); // n번 집의 최소 비용 찾기
		result = Math.min(result, dp[n][2]);
		
		System.out.println(result);
	}

}

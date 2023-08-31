package algorithm_java.DP;

import java.util.Arrays;
import java.util.Scanner;
// Baekjoon Online Judge 2225 합분해
public class bj2225 {

	static long mod = 1_000_000_000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		System.out.println(count(n,k));
	}

	public static long count(int n, int k) {
		long dp[][]= new long[k+1][n+1];
		
		for(int i = 0; i <= n; i++) // 누적합
			dp[1][i] = i+1;	
		
		for(int i = 2; i <= k; i++) { // k개를 더해서 만들 수 있는 경우의 수
			for(int j = 0; j <= n; j++) {	// n까지 경우의 수 구하기
				dp[i][j] = dp[i-1][j];	// 전 k의 dp 가져오기
				
				if(j != 0) dp[i][j] += dp[i][j-1];	// 현재 k의 dp 누적합
				
				dp[i][j] %= mod; //mod 연산
				
			}
		}
		return (dp[k][n] - dp[k][n-1] + mod) % mod; //+mod 하고 누적합이므로 -하고 출력
	}
}

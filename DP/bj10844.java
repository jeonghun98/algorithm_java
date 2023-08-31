package algorithm_java.DP;

import java.util.Scanner;
// Baekjoon Online Judge 10844 쉬운 계단 수
public class bj10844 {

	static long mod = 1000000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(stairs(n));

	}
	public static long stairs(int n) {
		long dp[][] = new long[n+1][10];
		
		for(int i = 1; i <= 9; i++) // 한 자릿수
			dp[1][i] = 1;
		
		for(int i = 2; i <= n; i++) { // 두 자릿수 이상
			for(int j = 0; j <= 9; j++) {
				if(j == 0) dp[i][j] = dp[i-1][j+1]; // 0이랑 9는 계단을 한 개씩만 만들 수 있다
				else if(j == 9) dp[i][j] = dp[i-1][j-1];
				else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
			}
		}
		
		
		for(int i = 1; i <= 9; i++) {
			dp[n][i] += dp[n][i-1]; //n인 계단 수 모두 합하기
		}

		return dp[n][9] % mod;
	}
}

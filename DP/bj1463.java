package algorithm_java.DP;

import java.util.Scanner;
// Baekjoon Online Judge 1463 1로 만들기
public class bj1463 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(operation(n));

	}
	
	public static int operation(int n) {
		int dp[] = new int[n+1];
		
		for(int i = 2; i < n + 1; i++) {
			dp[i] = dp[i-1]+1; //1을 뺀다는 연산 먼저
			
			if(i % 2 == 0)	// 나누어 떨어지면 현재 dp값과 몫의 dp값 중에 비교해서 저장
				dp[i] = Math.min(dp[i], dp[i/2]+1); 
			if(i % 3 == 0)
				dp[i] = Math.min(dp[i], dp[i/3]+1);
		}
		
		return dp[n];
	}
}

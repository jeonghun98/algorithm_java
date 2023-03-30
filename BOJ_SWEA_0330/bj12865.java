package algorithm_java.BOJ_SWEA_0330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj12865 {
	static int n,k;
	static int weight[];
	static int value[];
	
	public static int knapsack() {
		int dp[][] = new int[n+1][k+1]; // 가치
		
		for(int i = 1; i < n+1; i++) {		// 가방
			for(int j = 1; j < k+1; j++) { // 무게
				dp[i][j] = dp[i-1][j];
				
				if(weight[i] <= j) {
					dp[i][j] = Math.max(dp[i][j], value[i]+dp[i-1][j-weight[i]]);
				}
					
			}
		}
		
		return dp[n][k];
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        weight = new int[n+1];
        value = new int[n+1];
        
        for(int i = 1; i < n+1; i++) {
        	st = new StringTokenizer(br.readLine());
        	weight[i] = Integer.parseInt(st.nextToken());
        	value[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(knapsack());
        
	}

}

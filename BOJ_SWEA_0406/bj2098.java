package algorithm_java.BOJ_SWEA_0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2098 { // 외판원 순회 -> 비트 마스킹, DP, 백트래킹

	static int n;
	static int arr[][];
	static int dp[][];
	static final int INF = Integer.MAX_VALUE / 100;
	
	public static int tsp(int visited, int city) {
		// 모든 경로 방문 -> 시작 위치로 가기
		if(visited == (1<<n)-1) {
			if(arr[city][0] == 0) return INF;
			return arr[city][0];
		}
		
		// dp가 업데이트 되었다면 return
		if(dp[visited][city] != 0)
			return dp[visited][city];
		
		dp[visited][city] = INF;
		for(int i = 0; i < n; i++) {
			// 방문했던 곳이라면 or 갈 수 없는 곳이라면 continue
			if((visited & (1<<i)) != 0 || arr[city][i] == 0) continue;
			
			// 현재 위치에서 갈 수 있는 위치의 최단 경로 갱신
			int res = tsp(visited | (1<<i), i) + arr[city][i];
			dp[visited][city] = Math.min(dp[visited][city], res);
				
		}
		
		return dp[visited][city];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		dp = new int[(1<<n)][n];
		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(tsp(1, 0));

	}

}
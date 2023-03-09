package algorithm_java.BOJ_SWEA_0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj2533 {
	static int n;
	static ArrayList<Integer> [] list;
	static int dp[][];
	static boolean visited[];
	
	public static void dfs(int x) { // 0 -> 일반인, 1 -> 얼리 아답터
		visited[x] = true;
		dp[x][1] = 1;
		for(Integer ch : list[x]) {
			if(visited[ch]) continue;
			dfs(ch);
			dp[x][0] += dp[ch][1];
			dp[x][1] += Math.min(dp[ch][0], dp[ch][1]);
		}
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		for(int i = 1; i < n + 1; i++)
			list[i] = new ArrayList<>();
		
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		dp = new int[n+1][2];
		visited = new boolean[n+1];
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

}

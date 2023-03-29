package algorithm_java.BOJ_SWEA_0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj10971 {

	static int n;
	static int data[][];
	static boolean visited[];
	static int result;
	
	public static void TSP(int start, int now, int cnt, int weight) {
		if (weight > result) return;
		
		if(cnt == n-1 && data[now][start] != 0) {
			result = Math.min(result, weight + data[now][start]);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i]) continue;
			
			if(data[now][i] == 0) continue;
			
			visited[i] = true;
			TSP(start, i, cnt+1, weight + data[now][i]);
			visited[i] = false;
			
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		data = new int[n][n];
		visited = new boolean[n];
		result = Integer.MAX_VALUE;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			visited[i] = true;
			TSP(i,i,0,0);
			visited[i] = false;
		}
		
		System.out.println(result);

	}

}
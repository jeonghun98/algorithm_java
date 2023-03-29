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
		if (weight > result) return; // 비용이 현재 최종 min보다 크다면 return

		// n개를 모두 방문했고 현재위치에서 다시 출발했던 도시로 올 수 있다면
		if(cnt == n-1 && data[now][start] != 0) {
			result = Math.min(result, weight + data[now][start]); // 최종 값 갱신
			return;
		}
		
		for(int i = 0; i < n; i++) { // 순열
			if(visited[i] || data[now][i] == 0) continue; // 방문한 경로 or 갈 수 없는 경로라면 continue
			
			visited[i] = true; // 방문체크 -> 재귀 -> 방문해제
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

		// start 0 ~ n-1
		for(int i = 0; i < n; i++) {
			visited[i] = true;
			TSP(i,i,0,0);
			visited[i] = false;
		}
		
		System.out.println(result);

	}

}
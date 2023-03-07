package algorithm_java.BOJ_SWEA_0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj3584 {
	
	static int parents[];
	static boolean checked[];
	static int result;
	
	public static void findParent(int cur) {
		
		if(checked[cur]) { // 두 노드 중 앞의 노드가 먼저 방문했었다면 -> 공통 조상 
			result = cur;
			return;
		}

		checked[cur] = true; // 현재 노드 방문 
		
		if(parents[cur] != 0) // 부모 노드가 있다면 -> 재귀함수
			findParent(parents[cur]);
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			
			parents = new int[n+1]; // 부모 노드
			checked = new boolean[n+1]; // 노드 방문 체크
			
			for(int i = 1; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				parents[b] = a;
			}
			
			st = new StringTokenizer(br.readLine());
			int find_a = Integer.parseInt(st.nextToken());
			int find_b = Integer.parseInt(st.nextToken());
			
			result = 0;
			
			findParent(find_a);
			findParent(find_b);
			System.out.println(result);
			
		}
	}

}
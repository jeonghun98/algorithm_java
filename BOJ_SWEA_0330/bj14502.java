package algorithm_java.BOJ_SWEA_0330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14502 {

	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	static int n,m;
	static int data[][];
	static int tmp[][];
	static int max_cnt;
	
	
	public static void virus(int x, int y) { // 바이러스 확산
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx >= n || ny < 0 || ny >= m || tmp[nx][ny] != 0) continue;
			tmp[nx][ny] = 2;
			virus(nx, ny);
		}
	}
	
	public static void safe() { // 안전 지역 count -> 최댓값 갱신
		int cnt = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(tmp[i][j] == 0) cnt++;
			}
		}
		max_cnt = Math.max(max_cnt, cnt);
	}

	public static void copy() { // data copy
		for(int i = 0; i < n; i++) {
			tmp[i] = Arrays.copyOf(data[i], m);
		}
	}
	
	public static void dfs(int x, int y, int cnt) { 
		if(cnt == 3) { // 벽을 3개 세웠다면 data copy, virus 확산, safe 지역 count
			copy();
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(tmp[i][j] == 2) {
						virus(i,j);
					}
				}
			}
			safe();
			return;
		}
		
		for(int i = 0; i < n; i++) { //벽세우기
			for(int j = 0; j < m; j++) {
				if(data[i][j] == 0) {
					
					if(i < x) continue; // 전에 새웠던 벽이라면 continue
					if(i == x && j < y) continue;
					
					data[i][j] = 1;
					dfs(i,j,cnt+1);
					data[i][j] = 0;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data = new int[n][m];
        tmp = new int[n][m];
        for(int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < m; j++) {
        		data[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        max_cnt = 0;
        dfs(0,0,0);
        System.out.println(max_cnt);

	}

}

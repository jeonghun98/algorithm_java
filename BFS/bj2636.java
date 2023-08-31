package algorithm_java.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// Baekjoon Online Judge 2636 치즈
public class bj2636 {
	static int n,m;
	static int data[][];
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	static Queue<position> q;
	static int result = 0;
	static int piece = 0;
	static int air_index;
	
	public static class position { //위치랑 시간
		int x, y, time;

		public position(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static void air(int x, int y) { // 공기 확산
		if(data[x][y] == 1) return;
		
		data[x][y] = air_index;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx >= n || ny < 0 || ny >= m || data[nx][ny] == 1 || data[nx][ny] == air_index)
				continue;
			air(nx,ny);
		}
	}
	
	public static void cheese() { //치즈 녹이기
		piece = q.size(); //남은 조각
		int time = 0; //현재시간
		
		while(!q.isEmpty()) {
			position p = q.poll();
			
			if(time != p.time) { //현재 시간이랑 치즈의 시간이 같지 않다면 -> 공기 재확산, 현재시간 재설정
				air_index++;
				air(0,0);
				
				time = p.time;
				piece = q.size()+1;
			}
			
			boolean side = false;
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx < 0 || nx >= n || ny < 0 || ny >= m || data[nx][ny] == 0 || data[nx][ny] == 1)
					continue;
				side = true;
			}
			
			if(side) // 공기에 접촉한 치즈 녹이기
				data[p.x][p.y] = 0;	
			else // 공기에 닿지 않은 치즈 다시 넣기
				q.offer(new position(p.x,p.y,p.time+1));	
		}
		result = time+1; // 치즈 녹는데 걸리는 시간 = 현재시간 + 1
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		data = new int [n][m];
		q = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
				if(data[i][j] == 1) 
					q.offer(new position(i,j,0));
			}
		}
		
		air_index = 2; // 외부공기 2~
		air(0,0);
		cheese();
		System.out.println(result);
		System.out.println(piece);
	}
}
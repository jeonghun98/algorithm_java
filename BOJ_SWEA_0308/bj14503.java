package algorithm_java.BOJ_SWEA_0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj14503 {
	static int n,m;
	static int data[][];
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	public static class Pos{
		int x, y, d;

		public Pos(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static int bfs(int x, int y, int d) { // 0 : 청소할 칸, 2 : 청소된 칸, 1 : 벽
		int count = 0;
		
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(x,y,d));
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			if(data[now.x][now.y] == 0) {
				data[now.x][now.y] = 2;
				count++;
			}
			
			boolean forward = false;
			
			for(int i = 1; i <= 4; i++) { // 청소 되는 칸 찾기
				int nd = (now.d - i) < 0 ? 4 + (now.d - i) : (now.d - i);
				int nx = now.x + dx[nd];
				int ny = now.y + dy[nd];
				if(nx < 0 || nx >= n || ny < 0 || ny >= m || data[nx][ny] != 0) continue;
				q.add(new Pos(nx,ny,nd));
				forward = true;
				break;
			}
			
			if(!forward) { // 청소할 칸이 없다면 뒤로 back
				int nx = now.x - dx[now.d];
				int ny = now.y - dy[now.d];
				if(nx < 0 || nx >= n || ny < 0 || ny >= m || data[nx][ny] == 1) continue;
				q.add(new Pos(nx,ny,now.d));
			}
		}
		
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		data = new int[n][m];
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(x,y,dir));
	}

}

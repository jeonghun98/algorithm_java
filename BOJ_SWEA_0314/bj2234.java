package algorithm_java.BOJ_SWEA_0314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2234 {
	static int n,m;
	static int castle[][];
	static int data[][];
	static int walls[] = {1,2,4,8};
	static int dx[] = {0,-1,0,1};
	static int dy[] = {-1,0,1,0};
	static boolean wallCheck[];
	static int roomMaxSize;
	static ArrayList<Integer> roomSize;
	
	public static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void bfs(int x, int y, int cnt) { // bfs
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(x,y));
		castle[x][y] = cnt;
		int room = 1;
		
		while(!q.isEmpty()) {
			
			Pos now = q.poll();
			wall(now.x, now.y);
			
			for(int i = 0; i < 4; i++) {
				if(wallCheck[i]) continue;
				
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if(nx < 0 || nx > n - 1 || ny < 0 || ny > m - 1 || castle[nx][ny] == cnt) continue;
				
				castle[nx][ny] = cnt;
				q.add(new Pos(nx,ny));
				room++;
			}
		}
		if(roomMaxSize < room) roomMaxSize = room; // 넓은 방 크기 갱신
		roomSize.add(room); // 현재 방 크기 Arraylist에 넣기 -> 뒤에서 인접 방을 더하기 위해서
	}
	
	public static void wall(int x, int y) { // 벽 확인 비트마스킹
		int num;
		
		for(int i = 1; i < (i << 4); i++) {
			Arrays.fill(wallCheck, false);
			num = 0;
			
			for(int j = 0; j < 4; j++) {
				if((i & (1 << j)) != 0) {
					num += walls[j];
					wallCheck[j] = true;
				}
			}
			
			if(num == data[x][y]) {
				return;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		data = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				data[i][j] = Integer.parseInt(st.nextToken()); // 벽에 대한 정보
			}
		}
		
		castle = new int[n][m]; // bfs로 방 호수 저장
		wallCheck = new boolean[4]; // 좌상우하 벽 체크
		roomSize = new ArrayList<>(); // 방의 수 별로 크기 저장
		
		int count = 1; // 방 호수
		roomMaxSize = 1; //방의 최대 크기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(castle[i][j] == 0) // 방문하지 않은 방 = 호수가 정해지지 않은 방은 bfs
					bfs(i,j, count++);
			}
		}
		
		System.out.println(count - 1); // 방 호수 
		System.out.println(roomMaxSize); // 가장 넓은 방
		
		int MaxSize = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				for(int k = 0; k < 4; k++) { // 현재 위치의 좌상우하
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if(nx < 0 || nx > n - 1 || ny < 0 || ny > m - 1) continue; // 범위 체크
					
					// 현재 위치와 옆의 위치와 방 호수가 다르다면 => 두 방의 크기 합 구하기
					if(castle[i][j] != castle[nx][ny]) {
						MaxSize = Math.max(MaxSize, roomSize.get(castle[i][j]-1) + roomSize.get(castle[nx][ny]-1));
					}
				}
			}
		}
		System.out.println(MaxSize); // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
	}
}

package algorithm_java.BOJ_SWEA_0330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj4485 {

	static int n;
	static int data[][];
	static int dx[] = {0,-1,0,1};
	static int dy[] = {-1,0,1,0};
	
	public static class Pos{
		int value, x , y;

		public Pos(int value, int x, int y) { // 가치, 위치
			super();
			this.value = value;
			this.x = x;
			this.y = y;
		}

	}
	public static int zelda() {
		PriorityQueue<Pos> pq = new PriorityQueue<>(new Comparator<Pos>() {
			@Override
			public int compare(Pos p1, Pos p2) { // 오름차순
				return p1.value - p2.value;
			}
		});
		
		int value[][] = new int[n][n]; // 최소 금액 배열
		for(int i = 0; i < n; i++)
			Arrays.fill(value[i], Integer.MAX_VALUE);
		
		pq.add(new Pos(data[0][0],0,0));
		value[0][0] = data[0][0];
		
		while(!pq.isEmpty()) {
			Pos now = pq.poll();
			if(value[now.x][now.y] < now.value) continue; // 처리된 위치 continue
			
			for(int i = 0; i < 4; i++) { //사방탐색
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
				
				int rupee = now.value + data[nx][ny]; // 현재 rupee + 갈 수 있는 곳의 rupee 
				if(rupee < value[nx][ny]) {	// 해당 rupee가 최소 금액 배열 보다 작다면 갱신
					value[nx][ny] = rupee;
					pq.add(new Pos(rupee, nx, ny));
				}
			}
		}
		
		return value[n-1][n-1];
	}
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int cnt = 1;
        while(true) {
        	n = Integer.parseInt(br.readLine());
        	if(n == 0) return;
        	
        	data = new int[n][n];
        	
        	for(int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j = 0; j < n; j++) {
        			data[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	System.out.println("Problem " + cnt++ + ": " + zelda());
        }

	}

}

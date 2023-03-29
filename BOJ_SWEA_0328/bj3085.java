package algorithm_java.BOJ_SWEA_0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj3085 {

	static char data[][];
	static int n;
	
	static int dx[] = {1,0};
	static int dy[] = {0,1};
	
	static int max_len;
	public static void candy_max(int x, int y) { //현재 위치의 행과 열의 max 값 찾기
		
		for(int i = 0; i < 2; i++) {
			int len = 1;
			int nx = x, ny = y;
			
			if(i == 0) nx = 0; //현재 열 확인
			else ny = 0;	   //현재 행 확인
			
			char tmp = data[nx][ny];
			
			for(int j = 0; j < n-1 ; j++) { // max 값 찾기
				nx += dx[i];
				ny += dy[i];
				if(tmp != data[nx][ny]) {
					max_len = Math.max(max_len, len);
					len = 1;
					tmp = data[nx][ny];
				}
				else
					len++;
			}
			max_len = Math.max(max_len, len);
		}
	}
	
	public static void swap(int x, int y, int nx, int ny) {
		char tmp = data[x][y];
		data[x][y] = data[nx][ny];
		data[nx][ny] = tmp;
	}
	
	public static void Bomboni() { //봄보니
		for(int x = 0; x < n; x++) {
			for(int y = 0; y < n; y++) {
				candy_max(x,y); // 사탕 최대
				
				for(int i = 0; i < 2; i++) { //오른쪽하고 아래만 확인
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(nx >= n || ny >= n) break;
					if(data[nx][ny] == data[x][y]) continue; // 같다면 continue
					
					swap(x,y,nx,ny); // 다르다면 swap
					candy_max(x,y);  // 바꾼위치 둘다 행열 max값 찾기
					candy_max(nx,ny);
					swap(nx,ny,x,y); // 다시 원상복귀
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		data = new char[n][n];
		
		for(int i = 0; i < n; i++) {
			data[i] = br.readLine().toCharArray();
		}
		
		max_len = 0;
		Bomboni();
		System.out.println(max_len);
	}

}

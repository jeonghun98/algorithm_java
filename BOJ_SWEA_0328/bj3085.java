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
	public static void candy_max(int x, int y) {
		
		for(int i = 0; i < 2; i++) {
			int len = 1;
			int nx = x, ny = y;
			
			if(i == 0) nx = 0;
			else ny = 0;
			
			char tmp = data[nx][ny];
			
			for(int j = 0; j < n-1 ; j++) {
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
	
	public static void change() {
		for(int x = 0; x < n; x++) {
			for(int y = 0; y < n; y++) {
				candy_max(x,y);
				
				for(int i = 0; i < 2; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(nx >= n || ny >= n) break;
					if(data[nx][ny] == data[x][y]) continue;
					
					swap(x,y,nx,ny);
					candy_max(x,y);
					candy_max(nx,ny);
					swap(nx,ny,x,y);
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
		change();
		System.out.println(max_len);
	}

}

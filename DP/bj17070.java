package algorithm_java.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 17070 파이프 옮기기 1
public class bj17070 {
	static int n;
	static int data[][];
	
	static int dx[] = {0,-1,-1}; // 왼쪽, 위, 왼쪽 대각선 순서대로 확인
	static int dy[] = {-1,0,-1};
	
	public static int pipe() {
		int arr1[][] = new int[n][n]; //가로
		int arr2[][] = new int[n][n]; //세로
		int arr3[][] = new int[n][n]; //대각선
		
		arr1[0][1] = 1;
		
		for(int i = 0; i < n; i++) {
			for(int j = 2; j < n; j++) {
				if(data[i][j] == 1) continue; // 갈 수 없는 곳이라면 continue
				
				arr1[i][j] = arr1[i][j-1] + arr3[i][j-1]; // 가로 업데이트
				
				if(i < 1) continue;
				arr2[i][j] = arr2[i-1][j] + arr3[i-1][j]; // 세로 업데이트
				
				if(data[i][j-1] == 1 || data[i-1][j] == 1) continue; // 위, 왼쪽 확인 후 대각선 업데이트
				arr3[i][j] = arr1[i-1][j-1] + arr2[i-1][j-1] + arr3[i-1][j-1];
			}
		}

		return arr1[n-1][n-1] + arr2[n-1][n-1] + arr3[n-1][n-1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		data = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(pipe());

	}

}

package algorithm_java.BOJ_SWEA_0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea3307 {
	static int n;
	static int arr[];
	public static int LIS() {
		
		int dp[] = new int[n];
		int size = 0;
		for(int i = 0; i < n; i++) {
			
			int tmp = Arrays.binarySearch(dp, 0, size, arr[i]);
			tmp = Math.abs(tmp) -1;
			dp[tmp] = arr[i];
			
			if(size == tmp) size++; // 마지막 위치 => size증가
		}
		return size;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			System.out.println("#" + tc + " " + LIS());
		}
	}

}

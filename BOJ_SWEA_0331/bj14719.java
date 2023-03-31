package algorithm_java.BOJ_SWEA_0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14719 {
	static int h,w;
	static int data[];
	static int water;
	
	public static void rain(int height) {

		int minus = height - data[0]; // 최고 높이에서 시작 위치 크기 -> 해당 크기만큼 물 빼주기
		
		int tmp = data[0];
		for(int i = 0; i < w; i++) { // 왼쪽 -> 오른쪽
			if(tmp < data[i]) {	// 현재 제일 큰 높이보다 크다면
				minus = height - data[i]; // 갱신
				tmp = data[i];
			}
			water -= minus; // 물 빼주기
		}
		
		minus = height - data[w-1]; // 위와 같은 방법으로 오른쪽 -> 왼쪽
		tmp = data[w-1];
		for(int i = w-1; i >= 0; i--) {
			if(tmp < data[i]) {
				minus = height - data[i];
				tmp = data[i];
			}
			water -= minus;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		data = new int[w];
		
		int height = 0; // 최고 높이
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < w; i++) {
			data[i] = Integer.parseInt(st.nextToken());
			water += (h-data[i]); // 전체 hxw 빈칸의 물 채우기
			height = Math.max(data[i], height); //최고 높이
		}
		
		water -= (h-height) * w; // 최고 높이 위에 모두 제거
		
		rain(height);
		System.out.println(water);
	}

}

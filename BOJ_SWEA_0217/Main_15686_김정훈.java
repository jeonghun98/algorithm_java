package day10.Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15686_김정훈 {
	static int n, m;
	static int data[][];
	static List<int[]> chicken, home;
	static int result_min;
	static int isSelected[];
	
	public static int min_distance() { //각 집에서 선택된 m개의 치킨집 중에 가까운 치킨집의 거리를 저장
		int total_min_distance = 0;
		
		for(int h[] : home) {
			int min_distance = Integer.MAX_VALUE;
			for(int is : isSelected) {	// 선택된 m개의 치킨집 
				int ch_m[] = chicken.get(is);	//선택된 치킨집의 위치 가져와서 집이랑 거리 비교 => 가까운 치킨집 거리 저장
				min_distance = Math.min(Math.abs(h[0]-ch_m[0]) + Math.abs(h[1]-ch_m[1]), min_distance);
			}
			total_min_distance += min_distance;	// 각집에서 가까운 치킨집의 거리를 모두 저장
		}
		return total_min_distance;
	}
	
	public static void combi(int start, int cnt) { // 조합 재귀함수, 현재 치킨집에서 -> m만큼 뽑기
		if(cnt == m) {
			result_min = Math.min(min_distance(), result_min);	//m개의 치킨집이랑 각 집의 거리 계산 -> 작은 값을 최종값에 저장
			return;
		}
		for(int i = start; i < chicken.size(); i++) { // 뽑은 치킨집의 index 저장하고 재귀 함수 호출
			isSelected[cnt] = i;
			combi(i+1, cnt+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		data = new int[n][n]; 
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < n; c++) {
				data[r][c] = Integer.parseInt(st.nextToken());
				
				int[] tmp = {r,c};
				if(data[r][c] == 1) home.add(tmp);
				if(data[r][c] == 2) chicken.add(tmp);
			}
		}
		
		isSelected = new int[m]; //선택한 치킨집
		result_min = Integer.MAX_VALUE;	//도시의 치킨 거리의 최솟값
		
		if(chicken.size() == m) {	// 현재 치킨집 개수 = m
			for(int i = 0; i < m; i++)	//모든 치킨집을 선택후 min값 찾기
				isSelected[i] = i;
			result_min = min_distance();
		}
		else 
			combi(0,0); // 거리 조합 메소드
		
		System.out.println(result_min);
	}
}

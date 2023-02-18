package day10.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3040_김정훈 {
	static boolean isSelected[] = new boolean[9];
	static StringBuilder sb = new StringBuilder();
	static int data[] = new int[9];
	
	public static void select(int start, int cnt, int num_sum) { // 조합 메소드
		if(cnt==2) {
			if(num_sum != 100) return;	// 합이 100이 아니라면 return
			
			for(int i = 0; i < 9; i++) {	// 합이 100이라면 선택된 난쟁이 출력
				if(!isSelected[i])
					sb.append(data[i] + "\n");
			} return;
		}
		
		for(int i = start; i < 9; i++) {
			isSelected[i] = true;
			select(i+1, cnt+1, num_sum - data[i]);	//선택된 난쟁이를 제외하고 재귀함수 호출
			isSelected[i] = false;
		}
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int result = 0;
		for(int i = 0; i < 9; i++) {
			data[i] = Integer.parseInt(br.readLine());
			result += data[i];	// 9 난쟁이를 모두 더하고 -> 조합 메소드를 이용해서 2 난쟁이를 제외한다.
		}
		select(0,0,result);
		System.out.println(sb.toString()); // 출력
	}
	
}


package day10.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj2839 {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int data[] = new int[n+1];	//dp 배열
		int Max = Integer.MAX_VALUE;
		Arrays.fill(data, Max);

		for(int i = 1; i < n+1; i++) {
			int k = Integer.MAX_VALUE;
			if(i == 3) data[i] = 1;
			if(i == 5) data[i] = 1;
			
			if(i/3 > 0 && data[i-3] < Max ) // i-3 index가 있고 해당 값이 변경되어 있다면
				k = Math.min(data[i-3] + 1, k);	// i-3의 index값 + 1과 현재값 비교 -> 작은값
			if(i/5 > 0 && data[i-5] < Max)	//위와 동일한 방법(3->5)
				k = Math.min(data[i-5] + 1, k); 
			if(k < Max)	// k값이 max_value 에서 변경되었다면 데이터 변경
				data[i] = k;
		}
		
		if(data[n] < Max) 	//값이 수정되어 n킬로그램을 만들 수 있다면 출력
			System.out.println(data[n]);
		else				//그렇지 않다면 -1 출력
			System.out.println(-1);
		
//		Arrays.fill(data, -1);
//		data[0] = 0;
//		for(int i = 3; i <= N; i += 3) {
//			data[i] = i / 3;
//		}
//		
//		for(int i = 5; i <= N; i++) {
//			if(data[i - 5] != -1)  data[i] = data[i - 5] + 1;
//		}

	}
}
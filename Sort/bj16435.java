package algorithm_java.Sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// Baekjoon Online Judge 16435 스네이크버드
public class bj16435 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		int data[] = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			data[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(data);
		for(int num : data) {
			if(num > l) break;	// 키보다 높은 과일이면 break
			else l++;	// 작거나 같으면 과일 먹고 커지기
		}
		System.out.println(l);
	}
}

package algorithm_java.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class swea5658 {
	static ArrayDeque <Character> q;
	static ArrayList<String> list;
	static int n,k;
	
	public static int find() {
		
		for(int i = 0; i < n / 4; i++) { // n/4 번 회전
			for(int j = 0; j < 4; j ++) {	//각 벽의 암호 합치기
				String s= "";
				
				for(int l = 0; l < n / 4; l++) {
					char tmp = q.poll();
					s += tmp;
					q.offer(tmp);
				}
				
				if(!list.contains(s)) { //없는 암호라면 +
					list.add(s);
				}
			}
			q.offerFirst(q.pollLast()); // 끝에 있는 암호를 앞으로 넣기
		}
		 Collections.sort(list, Collections.reverseOrder()); // 오름차순
		 return Integer.parseInt(list.get(k-1),16); // k번째 10진수로 출력
	}
	
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<>();
			q = new ArrayDeque<>();
			
			String tmp = br.readLine();
			for(int i = 0; i < n; i++)
				q.offer(tmp.charAt(i));
				
			System.out.println("#" + tc + " " + find()); // 출력

		}
	}
}

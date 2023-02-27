package algorithm_java.BOJ_SWEA_0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class swea7645 {
	static int n,m;
	static int parents[];
	
	static int findSet(int a) { // 부모 찾기
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static void union(int x, int y) { // 묶기
		int a = findSet(x);
		int b = findSet(y);
		if(a==b) return;
		if(a < b) parents[b] = a;
		else parents[a] = b;
	}

	public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	m = Integer.parseInt(st.nextToken());
        	
        	parents = new int[n+1];
        	for(int i = 1; i < n+1; i++)
        		parents[i] = i;
        	
        	for(int i = 0; i < m; i++) {
        		st = new StringTokenizer(br.readLine());
        		int a = Integer.parseInt(st.nextToken()); // a와 b묶기
        		int b = Integer.parseInt(st.nextToken());
        		union(a,b);
        	}
        	
        	Set<Integer> h = new HashSet<>(); // 무리 번호 넣기
        	for(int i = 1; i < n+1; i++) {
    			h.add(findSet(i));
        	}
        	System.out.println("#" + tc + " " + h.size()); // 무리 개수 출력
        }
	}
}

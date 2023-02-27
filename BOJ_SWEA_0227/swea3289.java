package algorithm_java.BOJ_SWEA_0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea3289 {
	static int n,m;
	static int[] parents;
	
	static int findSet(int a) { //부모 찾기
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int x, int y) { // 집합 합치기
		int a = findSet(x);
		int b = findSet(y);
		if(a == b) return false;
	    if(a < b) parents[b] = a;
	    else parents[a] = b;
	    return true;
	}
	
	public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	m = Integer.parseInt(st.nextToken());
        	
        	parents = new int[n+1];
        	for(int i = 1; i < n+1; i++)
        		parents[i] = i;
        	
        	sb.append("#"+tc+" ");
        	for(int i = 0; i < m; i++) {
//        		System.out.println(Arrays.toString(parents));
        		st = new StringTokenizer(br.readLine());
        		int check = Integer.parseInt(st.nextToken());
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		
        		if(check == 0) union(a,b);	// 1이면 합치기
        		else
        			sb.append(findSet(a) == findSet(b) ? 1 : 0); //0이면 같은 집합인지 확인
        	}
        	sb.append("\n");
        }
        System.out.println(sb.toString());
	}

}

package algorithm_java.BOJ_SWEA_0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj2617 {
	static int n, m;
	static int half;
	
	public static int bead(int curr, boolean visited[], ArrayList<Integer>[] list) {
		int sum = 0; // 나보다 무겁거나 or 가벼운 구슬 count
		
		visited[curr] = true;
		
		for(int i : list[curr]) {
			if(!visited[i]) {
				sum += bead(i,visited,list) + 1;
			}
		}
		
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        half = (n+1)/2;
        
        ArrayList<Integer>[] list_light = new ArrayList[n+1]; // 해당 구슬보다 가벼운 구슬
        ArrayList<Integer>[] list_heavy = new ArrayList[n+1]; // 해당 구슬보다 무거운 구슬
        
        for(int i = 1; i < n+1; i++) {
        	list_light[i] = new ArrayList<>();
        	list_heavy[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	list_light[x].add(y);
        	list_heavy[y].add(x);
        }
        
        int result = 0;
        for(int i = 1; i < n+1; i++) {
        	if(bead(i, new boolean[n+1],list_light) >= half || // 무겁거나 가벼운 구슬이 전체의 반 이상이면 중간 x
        			bead(i, new boolean[n+1],list_heavy) >= half)
        		result++;
        }
        
        System.out.println(result);
	}
}

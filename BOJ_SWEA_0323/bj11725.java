package algorithm_java.BOJ_SWEA_0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 트리의 부모 찾기
public class bj11725 {
    static int n;
    static int parents[];
    static ArrayList<Integer>[] list;

    // dfs로 부모 노드 찾기
    public static void find(int index){
        for(int i : list[index]) {
            if(parents[i] == 0) {
                parents[i] = index;
                find(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        parents = new int[n+1];
        list = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < n - 1 ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        // 트리 루트는 1
        parents[1] = 1;
        find(1);
        for(int i = 2; i < n + 1; i++)
            System.out.println(parents[i]);
    }
}

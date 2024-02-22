package algorithm_java.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1068 트리
public class bj1068 {
    static int n;
    static int root;
    static int parent[];
    static boolean visited[];
    static boolean leaf[];
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        root = -1;
        parent = new int[n];
        visited = new boolean[n];
        leaf = new boolean[n];
        Arrays.fill(leaf, true);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            if(parent[i] == -1) root = i;
        }

        int remove = Integer.parseInt(br.readLine());
        visited[remove] = true;

        answer = 0;
        if(root != remove) dfs(root);
        System.out.println(answer);

    }
    public static void dfs(int idx) {
//        if(visited[idx]) return;
        visited[idx] = true;
        for(int i = 0; i < n; i++) {
            if(i == root) continue;
            if(idx == parent[i] && !visited[i]) {
                leaf[idx] = false;
                dfs(i);
            }
        }
        if(leaf[idx]) answer++;
    }
}

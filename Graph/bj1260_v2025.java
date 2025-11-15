package algorithm_java.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Baekjoon Online Judge 1260 DFS와 BFS
public class bj1260_v2025 {
    static int n, m, v;
    static ArrayList<Integer>[] list;

    public static void dfs(int start, boolean[] visited, StringBuilder sb) {
        sb.append(start);
        visited[start] = true;
        for(int i : list[start]) {
            if(!visited[i]) {
                sb.append(" ");
                dfs(i, visited, sb);
            }
        }
    }

    public static void bfs(int start, StringBuilder sb) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];

        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(" ");
            for(int i : list[cur]) {
                if(!visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        list = new ArrayList[n+1];

        for(int i = 0; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            list[n1].add(n2);
            list[n2].add(n1);
        }

        for(int i = 1; i <= n; i++) {
            Collections.sort(list[i]);
        }

        StringBuilder sb = new StringBuilder();
        dfs(v, new boolean[n+1], sb);
        sb.append("\n");
        bfs(v, sb);
        System.out.println(sb);
    }
}

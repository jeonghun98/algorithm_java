package algorithm_java.BOJ_SWEA_0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1260 {
    static int n,m,v;
    static boolean[][] adjMatrix; // 두 정점 사이 연결
    static boolean check[];	// 방문
    static StringBuilder sb;

    public static void dfs(int start) {	// dfs
        sb.append(start + " ");
        check[start] = true;
        for(int i = 1; i <= n; i++) {
            if(adjMatrix[start][i] && !check[i]) {
                dfs(i);
            }
        }
    }

    public static void bfs(int start) { // bfs
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(start); check[start] = true;

        while(!q.isEmpty()) {
            int curr = q.poll();
            sb.append(curr+" ");

            for(int i = 1; i <= n; i++) {
                if(adjMatrix[curr][i] && !check[i]) {
                    q.offer(i);
                    check[i] = true;
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

        adjMatrix = new boolean[n+1][n+1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjMatrix[from][to] = adjMatrix[to][from] = true;
        }

        sb = new StringBuilder();
        check = new boolean[n+1];

        dfs(v);
        sb.append("\n");

        check = new boolean[n+1];

        bfs(v);
        sb.append("\n");

        System.out.println(sb.toString());

    }
}

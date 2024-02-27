package algorithm_java.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1967 트리의 지름
public class bj1967 {
    static class Edge {
        int idx, cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    static int n;

    static ArrayList<Edge> edges[];
    static boolean visited[];
    static int endPoint;
    static int result;
    public static void dfs(int point, int len) {
        if(visited[point]) return;
        visited[point] = true;

        if(result < len) {
            result = len;
            endPoint = point;
        }

        for(Edge edge : edges[point]) {
            dfs(edge.idx, edge.cost + len);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        edges = new ArrayList[n+1];
        visited = new boolean[n+1];
        for(int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        endPoint = 0;
        result = 0;

        // 임의의 정점(root)에서 가장 멀리있는 정점 찾기
        dfs(1,0);
        Arrays.fill(visited, false);

        // 가장 멀리있는 정점 찾기
        dfs(endPoint, 0);
        System.out.println(result);
    }
}

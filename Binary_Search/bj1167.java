package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1167 트리의 지름
// 1967과 비슷
public class bj1167 {
    static int v;
    static class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    static int maxLen, maxPoint;
    static ArrayList<Node> list[];
    public static void dfs(int cur, boolean visited[], int len) {
        if(visited[cur]) return;
        visited[cur] = true;

        if(maxLen < len) {
            maxLen = len;
            maxPoint = cur;
        }

        for(Node next : list[cur]) {
            dfs(next.idx, visited, len + next.cost);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        v = Integer.parseInt(br.readLine());
        list = new ArrayList[v+1];
        boolean visited[] = new boolean[v+1];

        for(int i = 0; i <= v; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int end = Integer.parseInt(st.nextToken());
                if(end == -1) break;
                list[start].add(new Node(end, Integer.parseInt(st.nextToken())));
            }
        }

        maxLen = 0;
        maxPoint = 0;

        // 임의의 정점(root)에서 가장 멀리있는 정점 찾기
        dfs(1, visited, 0);
        Arrays.fill(visited, false);

        // 가장 멀리있는 정점 찾기
        dfs(maxPoint, visited, 0);
        System.out.println(maxLen);
    }
}

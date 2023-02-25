package algorithm_java.BOJ_SWEA_0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj13023 {
    static int n, k;
    static Node adj[];
    static boolean visited[];
    static int result;

    static class Node{ // Linked list node
        int vertex;
        Node link;

        public Node(int vertex, Node link) {
            this.vertex = vertex;
            this.link = link;
        }
    }

    private static void dfs(int current, int cnt) {

        if(cnt == 5) { // 친구 5명이면 return
            result = 1;
            return;
        }

        for(Node temp = adj[current]; temp != null; temp = temp.link) {
            if(!visited[temp.vertex]) {
                visited[temp.vertex] = true;	// 방문 체크
                dfs(temp.vertex, cnt+1);		// 현재 위치에서 갈 수 있는 노드가 있다면 해당 노드로 이동
                visited[temp.vertex] = false;	// 못 찾을 경우 방문 체크 해제
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        adj = new Node[n];
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a] = new Node(b, adj[a]);
            adj[b] = new Node(a, adj[b]);
        }

        result = 0;
        for(int i = 0; i < n; i++) {
            visited = new boolean[n];
            visited[i] = true;
            dfs(i,1);
            if(result == 1) break;
        }
        System.out.println(result);
    }
}

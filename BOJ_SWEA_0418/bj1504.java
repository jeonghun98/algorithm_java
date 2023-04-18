package algorithm_java.BOJ_SWEA_0418;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1504 특정한 최단 경로
public class bj1504 {
    static final int INF = Integer.MAX_VALUE;
    static int n, e;
    static ArrayList<Node> list[];
    static boolean find;

    static class Node{
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static int dijkstra(int start, int end) {
        int d[] = new int[n+1];
        Arrays.fill(d, INF);
        d[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(d[now.idx] < now.cost) continue;

            for(Node next : list[now.idx]) {
                int cost = next.cost + d[now.idx];
                if(cost < d[next.idx]) {
                    d[next.idx] = cost;
                    pq.add(new Node(next.idx, cost));
                }
            }
        }
        if (d[end] == INF) find = false;
        return d[end];
    }

    public static int find_min(int v1, int v2) {
        find = true;
        int result = dijkstra(1,v1) + dijkstra(v1, v2) + dijkstra(v2, n);

        if(!find) return -1;
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i < n + 1; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, cost));
            list[end].add(new Node(start, cost));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 -> N 으로 이동인데 v1, v2를 지나야하므로
        // 1 -> v1 -> v2 -> N의 최단경로를 구함
        System.out.println(Math.min(find_min(v1,v2),find_min(v2,v1)));

    }
}

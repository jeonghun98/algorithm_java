package algorithm_java.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1939 중량제한
public class bj1939 {
    static int n;
    static final long INF = Long.MAX_VALUE;
    static ArrayList<Node> list[];
    public static class Node{
        int idx;
        long cost;

        public Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    // kruskal도 가능 (start -> end로 이어주면 됨), BFS도 가능(+이진탐색)
    public static long dijkstra(int start, int end) {
        long dis[] = new long[n+1];
        dis[start] = INF;

        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> Math.toIntExact(o2.cost - o1.cost)));
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(dis[now.idx] > now.cost && now.idx != start) continue;

            for(Node next : list[now.idx]) {
                long cost = Math.min(next.cost, dis[now.idx]);
                if(cost > dis[next.idx]) {
                    dis[next.idx] = cost;
                    pq.add(new Node(next.idx, cost));
                }
            }
        }
        return dis[end];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i < n+1 ; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long cost = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, cost));
            list[end].add(new Node(start, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        System.out.println(dijkstra(start, end));
    }
}

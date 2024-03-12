package algorithm_java.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 14938 서강그라운드
public class bj14938 {
    static int n, m, r;
    static int item[];
    static class Node implements Comparable<Node>{
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            if (this.cost > o.cost) return 1;
            else return -1;
        }
    }
    static ArrayList<Node> list[];
    static final int  INF = Integer.MAX_VALUE;
    public static int dijkstra(int start) {
        int itemSum = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        int dis[] = new int[n+1];
        Arrays.fill(dis, INF);
        dis[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(dis[cur.idx] < cur.cost) continue;

            // 최단 경로로 갱신 후 범위 안이라면 item 얻기
            if(cur.cost <= m) itemSum += item[cur.idx];

            for(Node next : list[cur.idx]) {
                int cost = dis[cur.idx] + next.cost;
                if(cost < dis[next.idx]) {
                    dis[next.idx] = cost;
                    pq.add(new Node(next.idx, cost));
                }
            }
        }
        return itemSum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        item = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, l));
            list[b].add(new Node(a, l));
        }
        int result = 0;
        for(int i = 1; i <= n; i++) {
            result = Math.max(result, dijkstra(i));
        }
        System.out.println(result);
    }
}

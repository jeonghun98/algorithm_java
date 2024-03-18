package algorithm_java.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Baekjoon Online Judge 14284 간선 이어가기 2
public class bj14284 {
    static int n, m;
    static ArrayList<Node> list[];
    static final int MAX = Integer.MAX_VALUE;
    static class Node implements Comparable<Node>{
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    public static int dijkstar(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        int dis[] = new int[n+1];
        Arrays.fill(dis, MAX);
        dis[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(dis[cur.idx] < cur.cost) continue;

            for(Node next : list[cur.idx]) {
                int cost = dis[cur.idx] + next.cost;
                if(cost < dis[next.idx]) {
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
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n+1];
        for(int i = 0 ; i <= n; i++){
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        System.out.println(dijkstar(s,t));
    }
}

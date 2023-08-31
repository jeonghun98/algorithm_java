package algorithm_java.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 10282 해킹
public class bj10282 {
    static int n, c, d;
    static ArrayList<Node>[] list;
    static final int INF = Integer.MAX_VALUE;
    static class Node {
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static void dijkstra() { // 데이크스트라
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost-o2.cost);
        pq.add(new Node(c, 0));

        int dis[] = new int[n+1];
        Arrays.fill(dis, INF);
        dis[c] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(dis[now.idx] < now.cost) continue;

            for(Node next : list[now.idx]) {
                int cost = next.cost + dis[now.idx];
                if(dis[next.idx] > cost) {
                    dis[next.idx] = cost;
                    pq.add(new Node(next.idx, cost));
                }
            }
        }
        // 감연된 컴퓨터 count, 시간은 최장 시간 time -> 마지막 컴퓨터가 감염되기까지 걸리는 시간
        int cnt = 0, time = 0;
        for(int i = 1; i < n+1; i++) {
            if(dis[i] != INF) {
                time = Math.max(time, dis[i]);
                cnt++;
            }
        }
        System.out.println(cnt + " " + time);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list = new ArrayList[n+1];
            for(int i = 1; i < n+1; i++)
                list[i] = new ArrayList<>();

            for(int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                list[b].add(new Node(a, cost));
            }
            dijkstra();
        }
    }
}

package algorithm_java.BOJ_SWEA_0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Baekjoon 1238 파티
public class bj1238 {
    static int n, m, x;
    static final int INF = Integer.MAX_VALUE;
    static class Node{
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static int[] dijkstra(List<Node> list[]){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(x, 0));

        int dis[] = new int[n+1];
        Arrays.fill(dis, INF);
        dis[0] = 0; dis[x] = 0;

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
        return dis;
    }

    public static int maxStudent(int dis1[], int dis2[]){
        int max = 0;
        for(int i = 1; i < n+1; i++)
           if(max < dis1[i] + dis2[i]) max = dis1[i] + dis2[i];
        return max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        List<Node> goParty[] = new ArrayList[n+1];
        List<Node> goHome[] = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++) {
            goParty[i] = new ArrayList<>();
            goHome[i] = new ArrayList<>();
        }

        // 집 -> party(x) -> 집 의 max 값 구하기
        // 1) 집 -> party(x) : 반대로 dijkstra, 파티장에서 모든집으로 간다고 생각 => start 와 end 를 바꿔서 넣어줌
        // 2) party(x) -> 집 : 기본 dijkstra
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            goHome[a].add(new Node(b, cost));
            goParty[b].add(new Node(a, cost));
        }
        System.out.println(maxStudent(dijkstra(goParty), dijkstra(goHome)));
    }
}

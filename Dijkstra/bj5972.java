package algorithm_java.Dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// Baekjoon Online Judge 5972 택배 배송
public class bj5972 {
    static int n,m;
    static ArrayList<Node> list[];
    static class Node {
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static int dijkstra(){
        int dis[] = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = dis[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(1,0));

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
        return dis[n];
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }
        System.out.println(dijkstra());
    }
}

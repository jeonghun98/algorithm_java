package algorithm_java.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// Baekjoon Online Judge 1753 최단경로
public class bj1753 {

    static int V,E;
    static ArrayList<Node>[] list;
    static PriorityQueue<Node> pq;
    static int distance[];
    final static int INF = Integer.MAX_VALUE;

    public static class Node implements Comparable<Node>{ // 정점과 가중치
        int node, weight;

        public Node(int node, int weight) {
            super();
            this.node = node;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) { // 가중치 오름차순
            return this.weight - o.weight;
        }
    }

    public static void dijkstra(int start) {
        pq = new PriorityQueue<>();

        pq.offer(new Node(start, 0)); // 시작점을 우선순위큐에 넣고 시작
        distance[start] = 0;			// 시작점 최단 경로 0

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(distance[now.node] < now.weight) continue; // 이미 처리되었다면 continue

            for(Node n : list[now.node]) {	// 해당 정점에서 갈 수 있는 인접 정점 확인
                int cost = now.weight + n.weight; // 현재 노드를 거쳐 다르 노드로 이동하는 거리가 짧다면 -> distance갱신 후 우선순위큐에 삽입
                if(cost < distance[n.node]) {
                    distance[n.node] = cost;
                    pq.offer(new Node(n.node, cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        list = new ArrayList[V+1];
        for(int i = 0; i < V+1; i++)
            list[i] = new ArrayList<>();

        distance = new int[V+1];
        Arrays.fill(distance, INF);

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v,w)); // u -> v로 가는 것을 list에 w와 함께 Node로 저장
        }

        dijkstra(start); // 다익스트라

        for(int i = 1; i < V+1; i++) {
            if(distance[i] == INF) // 경로 존재 x -> INF
                System.out.println("INF");
            else
                System.out.println(distance[i]);
        }
    }
}

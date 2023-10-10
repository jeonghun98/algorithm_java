package algorithm_java.Kruskal;

import java.util.*;

// programmers 49189 가장 먼 노드
public class pm49189 {
        static class Node {
            int ver; int edge;
            Node (int ver, int edge) {
                this.ver = ver;
                this.edge = edge;
            }
        }
        public static int dijkstra(int n) {
            int dis[] = new int[n+1];
            Arrays.fill(dis, Integer.MAX_VALUE);
            dis[1] = 0;
            int max = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.edge - o2.edge);
            pq.add(new Node(1, 0));
            while(!pq.isEmpty()) {
                Node cur = pq.poll();
                if(cur.edge < dis[cur.ver]) continue;
                for(Node next : list[cur.ver]) {
                    int cost = dis[cur.ver] + next.edge;
                    if(cost < dis[next.ver]) {
                        dis[next.ver] = cost;
                        pq.add(new Node(next.ver, cost));
                        max = Math.max(max, cost);
                    }
                }
            }
            int result = 0;
            for(int i = 2; i < n+1; i++)
                if(dis[i] == max) result++;

            return result;
        }
        static ArrayList<Node>[] list;

        public static int solution(int n, int[][] edge) {
            int answer = 0;
            list = new ArrayList[n+1];
            for(int i = 0; i < n+1; i++) {
                list[i] = new ArrayList<>();
            }
            for(int i = 0; i < edge.length; i++) {
                int start = edge[i][0];
                int end = edge[i][1];
                list[start].add(new Node(end, 1));
                list[end].add(new Node(start,1));
            }
            return dijkstra(n);
        }
    public static void main(String[] args) {
        System.out.println(solution(6, new int[][] {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}}));
    }
}

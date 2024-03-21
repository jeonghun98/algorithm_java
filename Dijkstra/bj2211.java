package algorithm_java.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Baekjoon Online Judge 2211 네트워크 복구
public class bj2211 {
    static int n, m;
    static ArrayList<Node> list[];
    static final int MAX = Integer.MAX_VALUE;

    static class Node {
        int idx, cost;
        int before;

        Node(int idx, int cost) {
            this(idx, cost, 0);
        }

        Node(int idx, int cost, int before) {
            this.idx = idx;
            this.cost = cost;
            this.before = before;
        }
    }

        public static void dijkstra() {
            int dis[] = new int[n + 1];
            Arrays.fill(dis, MAX);
            dis[1] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
            pq.add(new Node(1, 0));

            int cnt = 0;
            StringBuilder sb = new StringBuilder();

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (dis[cur.idx] < cur.cost) continue;

                // 최단 경로의 전 노드와 현재 노드의 위치를 출력하면
                // 각 노드들의 최단 경로를 모두 출력할 수 있다.
                if (cur.before != 0) {
                    cnt++;
                    sb.append(cur.before + " " + cur.idx + "\n");
                }

                for (Node next : list[cur.idx]) {
                    int cost = cur.cost + next.cost;
                    if (cost < dis[next.idx]) {
                        dis[next.idx] = cost;
                        pq.add(new Node(next.idx, cost, cur.idx));

                    }
                }
            }
            System.out.println(cnt);
            System.out.print(sb);
        }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, cost));
            list[end].add(new Node(start, cost));
        }
        dijkstra();
    }
}
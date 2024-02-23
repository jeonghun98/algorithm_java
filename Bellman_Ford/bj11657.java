package algorithm_java.Bellman_Ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Baekjoon Online Judge 11657 타임머신
public class bj11657 {
    static final long MAX = Long.MAX_VALUE;
    static int n, m;
    static class Edge{
        int start, end, cost;
        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, cost));
        }

        // 500 * 6000 * 10000이 int의 범위를 넘어감
        long[] dist = new long[n+1];
        Arrays.fill(dist, MAX);
        dist[1] = 0;

        // n - 1 번의 반복작업과 마지막 확인작업을 한 번에 돌림
        for(int i = 1; i <= n; i++) {
            for(Edge edge : edges) {
                // 시작도시가 초기화 그대로라면 pass
                if(dist[edge.start] == MAX) continue;
                if(dist[edge.start] + edge.cost < dist[edge.end]) {
                    dist[edge.end] = dist[edge.start] + edge.cost;

                    // n번째 작업에서 값이 변경되면 무한히 되돌아 갈 수 있다는 뜻
                    if(i == n) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                }
            }
        }
        for(int i = 2; i <= n; i++) {
            if(dist[i] == MAX) System.out.println(-1);
            else System.out.println(dist[i]);
        }

    }
}

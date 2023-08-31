package algorithm_java.Kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 16398 행성 연결
public class bj16398 {

    public static class Edge implements Comparable<Edge>{
        int v1, v2, weight;
        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }
    public static int find_parent(int parent[], int x) { // 부모 노드 찾기 + update
        if(parent[x] != x)
            parent[x] = find_parent(parent,parent[x]);
        return parent[x];
    }

    public static void union_parent(int parent[], int a, int b) { // 연결
        a = find_parent(parent, a);
        b = find_parent(parent,b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());

        int parent[] = new int[n+1];
        for(int i = 0; i <= n; i++)  parent[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int weight = Integer.parseInt(st.nextToken());
                if(i < j) {
                    pq.add(new Edge(i+1,j+1, weight));
                }
            }
        }

        long weight = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(find_parent(parent, cur.v1) != find_parent(parent, cur.v2)) { // 연결되지 않았다면
                union_parent(parent, cur.v1, cur.v2);   // 연결하고
                weight += cur.weight;                   // 관리비용 update
            }
        }
        System.out.println(weight);
    }
}

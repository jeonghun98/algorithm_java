package algorithm_java.BOJ_SWEA_0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1647 도시 분할 계획
public class bj1647 {
    static int n, m;
    static PriorityQueue<Node> pq;
    static class Node{
        int start, end, cost;
        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    public static int find_parent(int parent[], int x) {
        if(parent[x]!= x)
            parent[x] = find_parent(parent, parent[x]);
        return parent[x];
    }

    public static void union_parent(int parent[], int a, int b) {
        a = parent[a];
        b = parent[b];
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static int kruskal() {
        int parent[] = new int[n+1];
        for(int i = 1 ; i < n+1; i++)
            parent[i] =  i;

        int total = 0, max = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(find_parent(parent, now.start) != find_parent(parent, now.end)) {
                union_parent(parent, now.start, now.end);
                total += now.cost;
                max = Math.max(max, now.cost);
            }
        }

        return total - max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        for(int i = 0; i < m ;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Node(start, end ,cost));
        }

        System.out.println(kruskal());
    }
}

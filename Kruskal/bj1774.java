package algorithm_java.Kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1774 우주신과의 교감
public class bj1774 {
    static class Node{
        int start, end;
        double cost;
        public Node(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    public static int find_parent(int parent[], int x) { // 부모노드 찾기
        if(parent[x] != x)
            parent[x] = find_parent(parent, parent[x]);
        return parent[x];
    }

    public static void union_parent(int parent[], int a, int b) { // 노드 연결
        a = find_parent(parent, a);
        b = find_parent(parent, b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int parent[] = new int[n+1];
        for(int i = 0; i < n+1; i++)
            parent[i] = i;

        int arr[][] = new int[n+1][2];
        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union_parent(parent, a, b);
        }

        // 각 좌표들끼리의 거리를 pq에 넣음, 거리 오름차순
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o.cost));
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < n+1; j++) {
                if(i == j && find_parent(parent, i) == find_parent(parent, j)) continue;
                double cost = Math.sqrt(Math.pow(arr[i][0]-arr[j][0], 2) + Math.pow(arr[i][1]-arr[j][1], 2));
                pq.add(new Node(i,j,cost));
            }
        }

        // 연결되지 않은 통로 연결
        double length = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(find_parent(parent,now.start) != find_parent(parent, now.end)) {
                union_parent(parent,now.start, now.end);
                length += now.cost;
            }
        }
        System.out.printf("%.2f", length);
    }
}

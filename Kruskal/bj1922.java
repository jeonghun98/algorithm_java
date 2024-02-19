package algorithm_java.Kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Baekjoon Online Judge 1922 네트워크 연결
public class bj1922 {
    static int n, m;
    static PriorityQueue<Node> pq;
    static ArrayList<Node> list;
    static class Node implements Comparable <Node> {
        int start, end, cost;
        Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    public static int findParent(int a, int[] parent) {
        if(parent[a] != a)
            parent[a] = findParent(parent[a], parent);
        return parent[a];
    }
    public static void unionParent(int a, int b, int[] parent) {
        a = findParent(a, parent);
        b = findParent(b, parent);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
    public static int kruskal() {
        int sum = 0;

        int parent[] = new int[n+1];
        for(int i = 1; i < n+1; i++)
            parent[i] = i;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(findParent(cur.start, parent) != findParent(cur.end, parent)) {
                unionParent(cur.start, cur.end, parent);
                sum += cur.cost;
            }
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
//        pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        list = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
//            pq.add(new Node(a, b, c));
            list.add(new Node(a,b,c));
        }
//        System.out.println(kruskal());

        Collections.sort(list);

        int parent[] = new int[n+1];
        for(int i = 1; i < n+1; i++)  parent[i] = i;

        int sum = 0;
        for(Node cur : list) {
            if(findParent(cur.start, parent) != findParent(cur.end, parent)) {
                unionParent(cur.start, cur.end, parent);
                sum += cur.cost;
            }
        }
        System.out.println(sum);
    }
}

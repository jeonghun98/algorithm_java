package algorithm_java.BOJ_SWEA_0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj11779 {
    static int n, m;
    static final int INF = Integer.MAX_VALUE;
    static class Node{
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    static class Distance{
        int dis;
        ArrayList<Integer> path;
        public Distance(int dis) {
            this.dis = dis;
        }
    }
    public static ArrayList<Integer> copyPath(ArrayList<Integer> path) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for(Integer i : path)
            tmp.add(i);
        return tmp;
    }
    public static Distance[] dijkstra(List<Node> list[], int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0));

        Distance d[] = new Distance[n+1];
        for(int i = 1; i < n+1; i++) d[i] = new Distance(INF);
        d[start].dis = 0;

        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(start);
        d[start].path = tmp;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(d[now.idx].dis < now.cost) continue;

            for(Node next : list[now.idx]) {
                int cost = next.cost + d[now.idx].dis;
                if(d[next.idx].dis > cost) {
                    d[next.idx].dis = cost;
                    d[next.idx].path = copyPath(d[now.idx].path);
                    d[next.idx].path.add(next.idx);
                    pq.add(new Node(next.idx, cost));
                }
            }
        }
        return d;
    }

    public static void print(Distance[] d, int end) {
        System.out.println(d[end].dis);
        System.out.println(d[end].path.size());
        for(Integer i : d[end].path)
            System.out.print(i + " ");
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        List<Node> list[] = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, cost));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        print(dijkstra(list, start), end);
    }
}

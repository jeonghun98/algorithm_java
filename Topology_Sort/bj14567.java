package algorithm_java.Topology_Sort;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 14567 선수과목
public class bj14567 {
    static int n, m;
    static ArrayList<Integer>[]list;
    static int indegree[];
    static class Node {
        int idx, cnt;
        Node (int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
    public static int[] topologySort() {
        int cnt[] = new int[n+1];

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.cnt - o2.cnt);
        for(int i = 1; i < n+1; i++) {
            if(indegree[i] == 0) {
                pq.add(new Node(i,1));
            }
        }

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            cnt[cur.idx] = cur.cnt;
            for(int next : list[cur.idx]) {
                indegree[next]--;
                if(indegree[next] == 0) {
                    pq.add(new Node(next, cur.cnt + 1));
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n+1];
        indegree = new int[n+1];

        for(int i = 1; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            indegree[b]++;
        }
        int[] answer = topologySort();
        for(int i = 1; i < n+1; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}

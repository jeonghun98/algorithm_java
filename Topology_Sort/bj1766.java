package algorithm_java.Topology_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1766 문제집
public class bj1766 {
    static int n, m;
    static ArrayList<Integer> []list;
    static int []indegree;
    static StringBuilder sb;
    public static void topologySort(){
        sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i < n+1; i++) {
            if(indegree[i] == 0) pq.add(i);
        }

        while(!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur + " ");

            for(int next : list[cur]) {
                if(--indegree[next] == 0) {
                    pq.add(next);
                }
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }
        indegree = new int[n+1];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list[start].add(end);
            indegree[end]++;
        }

        topologySort();
        System.out.println(sb.toString());
    }
}

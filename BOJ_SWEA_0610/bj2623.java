package algorithm_java.BOJ_SWEA_0610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Baekjoon Online Judge 2623 음악프로그램
public class bj2623 {
    static int n, m;
    static ArrayList<Integer> list[];
    static int indegree[];
    static StringBuilder sb;
    public static String topologySort() {
        sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i < n+1; i++) {
            if(indegree[i] == 0) q.add(i);
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur + "\n");
            for(int next : list[cur]) {
                indegree[next]--;
                if(indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        for(int i = 1; i < n+1; i++) {
            if(indegree[i] != 0) {
                return "0";
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }

        indegree = new int[n+1];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end;
            for(int j = 1; j < k; j++) {
                end = Integer.parseInt(st.nextToken());
                list[start].add(end);
                indegree[end]++;
                start = end;
            }
        }
        System.out.print(topologySort());
    }
}

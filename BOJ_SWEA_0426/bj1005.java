package algorithm_java.BOJ_SWEA_0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1005 ACM Craft
public class bj1005 {
    static int indegree[];
    static int time[];
    static ArrayList<Integer> list[];
    public static int topology_sort(int n, int find) { // dfs도 가능
        int result[] = new int[n+1]; // 건설 시간
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i < n+1; i++) { // 들어오는 간선의 개수가 0이면 q에 넣고 건설 시간 update
            if(indegree[i] == 0)  {
                q.add(i);
                result[i] = time[i];
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next : list[cur]) {
                result[next] = Math.max(result[next], result[cur] + time[next]); // 최댓값으로 갱신
                indegree[next]--;   // 간선수 -1
                if(indegree[next] == 0) // 들어오는 간선의 수가 0이라면 q에 넣기
                    q.add(next);
            }
        }
        return (result[find]);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            time = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < n+1; i++)
                time[i] = Integer.parseInt(st.nextToken());

            indegree = new int[n+1];
            list = new ArrayList[n+1];
            for(int i = 1; i < n+1; i++)
                list[i] = new ArrayList<>();

            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                list[start].add(end);
                indegree[end] += 1;
            }

            int find = Integer.parseInt(br.readLine());
            System.out.println(topology_sort(n, find));
        }
    }
}

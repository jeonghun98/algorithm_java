package algorithm_java.이분그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj1707 {
    static ArrayList<Integer> [] list;
    public static void dfs(int color[], int x, int c) {
        color[x] = c;
        for(int i : list[x]) {
            if(color[i] == 0) {
                dfs(color, i, 3-c);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            list = new ArrayList[V+1];
            for(int i = 1; i <= V; i++)
                list[i] = new ArrayList<>();

            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                list[u].add(v);
                list[v].add(u);
            }

            int color[] = new int[V+1];
            for(int i = 1; i <= V; i++) {
                if(color[i] == 0) {
                    dfs(color, i, 1);
                }
            }

            boolean flag = true;
            for(int i = 1; i <= V; i++) {
                for(int j : list[i]) {
                    if(color[i] == color[j]) {
                        flag = false;
                    }
                }
            }

            sb.append(flag ? "YES" : "NO");
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
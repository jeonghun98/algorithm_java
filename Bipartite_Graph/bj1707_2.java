package algorithm_java.Bipartite_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// Baekjoon Online Judge 1707 이분 그래프
public class bj1707_2 {
    static ArrayList<Integer> [] list;
    public static boolean dfs(int color[], int x, int c) {
        color[x] = c;
        for(int i : list[x]) {
            if(color[i] == 0) {
                if(!dfs(color, i, 3-c)) // 0 => 1,2
                    return false;
            } else if(color[i] == color[x]){
                return false;
            }
        }
        return true;
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

            boolean bipatite = true;
            int color[] = new int[V+1];
            for(int i = 1; i <= V; i++) {
                if(color[i] == 0) {
                    if (!dfs(color, i, 1)) { // 0 => 1,2
                        bipatite = false;
                        break;
                    }
                }
            }

            sb.append(bipatite ? "YES" : "NO");
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
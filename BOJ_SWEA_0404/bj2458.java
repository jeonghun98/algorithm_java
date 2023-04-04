package algorithm_java.BOJ_SWEA_0404;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj2458 {

    public static void dfs(int idx, boolean visited[], List<Integer> list[]) {
        visited[idx] = true;
        for(Integer i : list[idx]) {
            if(!visited[i]) {
                dfs(i, visited, list);
            }
        }
    }

    public static boolean check(int n, boolean visited[]) { // 모두 방문 가능하면 -> 자신의 위치 파악 가능
        for(int i = 1; i <= n; i++)
            if(!visited[i]) return false;
        return true;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Integer> light[] = new ArrayList[n+1];
            List<Integer> heavy[] = new ArrayList[n+1];
            for(int i = 1; i <= n; i++) {
                light[i] = new ArrayList<>();
                heavy[i] = new ArrayList<>();
            }

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                heavy[a].add(b);
                light[b].add(a);
            }

            int student = 0;
            for(int i = 1; i <= n; i++) {
                boolean visited[] = new boolean[n+1];
                dfs(i,visited,heavy);
                dfs(i,visited,light);
                if(check(n, visited)) student++;
            }
            System.out.println(student);
    }
}

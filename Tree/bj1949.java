package algorithm_java.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1949 우수 마을
public class bj1949 {
    static int n;
    static int resident[];
    static ArrayList<Integer> list[];
    static int dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        resident = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            resident[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[n+1];
        for(int i = 0; i <= n ; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        dp = new int[n+1][2]; // [n][0] -> 우수마을 x, [n][1] -> 우수마을 o
        dfs(1,0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void dfs(int n, int parent) {
        for(int next : list[n]) {
            if(next != parent) {
                dfs(next, n);
                dp[n][0] += Math.max(dp[next][0], dp[next][1]); // n이 우수마을 x -> 자식 노드에서 최댓값 찾기
                dp[n][1] += dp[next][0];                        // n이 우수마을 o -> 자식 노드가 선택 x
            }
        }
        dp[n][1] += resident[n]; // n이 우수마을 o -> 자기자신 더하기
    }
}

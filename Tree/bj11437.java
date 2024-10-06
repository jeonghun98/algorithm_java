package algorithm_java.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Baekjoon Online Judge 11437 LCA
public class bj11437 {
    static ArrayList<Integer> tree[];
    static int[] parents, level;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++) {
            tree[i] = new ArrayList<>();
        }

        level = new int[n+1];
        parents = new int[n+1];
        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        init(1,1,0);

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCA(a, b) + "\n");
        }
        System.out.print(sb);
    }
    public static void init(int cur, int depth, int parent) {
        level[cur] = depth;
        parents[cur] = parent;
        for(int next : tree[cur]) {
            if(next != parent) {
                init(next, depth + 1, cur);
            }
        }
    }

    public static int LCA (int n1, int n2) {
        // n1 level < n2 level 세팅
        if(level[n1] > level[n2]) {
            int tmp = n1;
            n1 = n2;
            n2 = tmp;
        }

        // 높이 맞추기
        while(level[n1] != level[n2]) {
            n2 = parents[n2];
        }

        // LCA 찾기
        while(n1 != n2) {
            n1 = parents[n1];
            n2 = parents[n2];
        }

        return n1;
    }
}

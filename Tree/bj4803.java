package algorithm_java.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// Baekjoon Online Judge 4803 트리
public class bj4803 {
    static int n,m;
    public static int findParent(int a, int parent[]) {
        if(parent[a] != a){
            parent[a] = findParent(parent[a], parent);
        }
        return parent[a];
    }
    public static void unionParent(int a, int b, int parent[]){
        a = findParent(a, parent);
        b = findParent(b, parent);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int parent[];
        int idx = 1, cnt = 0;
        while(true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;

            parent = new int[n+1];
            for(int i = 1; i < n+1; i++)
                parent[i] = i;

            boolean cycle = false;
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int aP = findParent(a, parent);
                int bP = findParent(b, parent);
                if(aP != bP) unionParent(a, b, parent);
                else parent[aP] = 0;

            }
            if(!cycle) {
                HashSet<Integer> set = new HashSet<>();
                for(int i = 1; i < n+1; i++) {
                    if(findParent(i, parent) != 0) set.add(parent[i]);
                }
                cnt = set.size();
            }
            switch (cnt) {
                case 0 : sb.append("Case " + idx + ": No trees.\n"); break;
                case 1 : sb.append("Case " + idx + ": There is one tree.\n"); break;
                default : sb.append("Case " + idx + ": A forest of " + cnt + " trees.\n"); break;
            }
            idx++;
            cnt = 0;
        }
        System.out.print(sb);
    }
}

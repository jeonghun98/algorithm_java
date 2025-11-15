package algorithm_java.Kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

// Baekjoon Online Judge 1043 거짓말
public class bj1043_v2025 {
    public static int findParent(int a, int[] parent) {
        if(parent[a] != a) parent[a] = findParent(parent[a], parent);
        return parent[a];
    }
    public static void unionFind(int a, int b, int[] parent) {
        a = findParent(a, parent);
        b = findParent(b, parent);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] parent = new int[n+1];
        int[] party = new int[m];
        for(int i = 1; i <= n; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        int trueCnt = Integer.parseInt(st.nextToken());
        for(int i = 0; i < trueCnt; i++) {
            parent[Integer.parseInt(st.nextToken())] = 0;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int partyCnt = Integer.parseInt(st.nextToken());
            int person1 = party[i] = Integer.parseInt(st.nextToken());
            for(int j = 1; j < partyCnt; j++) {
                int person2 = Integer.parseInt(st.nextToken());
                unionFind(person1, person2, parent);
            }
        }

        int result = 0;
        for(int i = 0; i < m; i++) {
            if(findParent(party[i], parent) != 0) result++;
        }
        System.out.println(result);
    }
}

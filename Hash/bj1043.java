package algorithm_java.Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1043 거짓말
public class bj1043 {
    public static int findParent(int[] parent, int x) {
        if(parent[x] != x) {
            parent[x] = findParent(parent, parent[x]);
        }
        return parent[x];
    }
    public static void unionParent(int[] parent, int a, int b) {
        a = findParent(parent, a);
        b = findParent(parent, b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        if(k == 0) {
            System.out.println(m);
            return;
        }

        int parent[] = new int[n+1];
        for(int i = 1; i <= n; i++)
            parent[i] = i;

        for(int i = 0; i < k; i++) // 진실은 아는 사람만 0
            parent[Integer.parseInt(st.nextToken())] = 0;


        int party[] = new int[m];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            int host = party[i] = Integer.parseInt(st.nextToken());
            for(int j = 1; j < k; j++) {
                // 첫번째 파티 참가한 사람 : host -> 나머지 사람들과 unionParent
                unionParent(parent, host, Integer.parseInt(st.nextToken()));
            }
        }

        int result = 0;
        for(int i = 0; i < m; i++) {
            if(findParent(parent, party[i]) != 0) { // 진실을 아는 사람이 없다면 파티 참가
                result++;
            }
        }
        System.out.println(result);
    }
}

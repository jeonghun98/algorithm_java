package algorithm_java.Subset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 1182 부분수열의 합
public class bj1182 {
    static int n, s;
    static int data[];
    static int result;
    public static void dfs(int sum, int cnt, int sumCnt) {
        if(cnt == n) {
            if(sumCnt!=0 && sum == s) result++;
            return;
        }
        dfs(sum+data[cnt], cnt+1, sumCnt+1);
        dfs(sum, cnt+1, sumCnt);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        data = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        result=0;
        dfs(0,0, 0);
        System.out.println(result);
    }
}
package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// Baekjoon Online Judge 12738 가장 긴 증가하는 부분 수열 3
public class bj12738 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int size = 0;
        Long dp[] = new Long[n];
        dp[size] = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            Long data = Long.parseLong(st.nextToken());
            int tmp = Arrays.binarySearch(dp, 0, size, data);
            if (tmp >= 0) continue;

            tmp = Math.abs(tmp) - 1;
            dp[tmp] = data;
            if (size == tmp) size++;
        }

        System.out.println(size);
    }
}

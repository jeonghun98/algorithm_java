package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 2565 전깃줄
public class bj2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int data[] = new int[501];
        Arrays.fill(data, -1);

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            data[from] = to;
        }

        int size = 0;
        int dp[] = new int[501];
        for(int i = 1; i < 501; i++) {
            if(data[i] == -1) continue;
            int tmp = Arrays.binarySearch(dp, 0, size, data[i]);
            if(tmp >= 0) continue;

            tmp = Math.abs(tmp) - 1;
            dp[tmp] = data[i];
            if(size == tmp) size++;
        }
        System.out.println(n-size);
    }
}

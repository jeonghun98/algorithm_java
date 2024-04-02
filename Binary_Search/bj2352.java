package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 2352 반도체 설계
public class bj2352 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int data[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        int size = 0;
        int dp[] = new int[n];
        for(int i = 0; i < n; i++) {
            int tmp = Arrays.binarySearch(dp, 0, size, data[i]);
            if(tmp >= 0) continue;

            tmp = Math.abs(tmp)-1;
            dp[tmp] = data[i];
            if(size == tmp) size++;
        }
        System.out.println(size);
    }
}

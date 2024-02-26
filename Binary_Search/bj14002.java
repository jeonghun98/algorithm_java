package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 14002 가장 긴 증가하는 부분 수열 4
public class bj14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int dp[] = new int[n];
        int arr[] = new int[n];
        int idx[] = new int[n];
        int size = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int data = Integer.parseInt(st.nextToken());
            int tmp = Arrays.binarySearch(dp, 0, size, data);
            arr[i] = data;
            if (tmp < 0) {
                tmp = Math.abs(tmp) - 1;
                dp[tmp] = data;
            }
            if (size == tmp) size++;
            idx[i] = tmp;
        }

        sb.append(size + "\n");

        int result[] = new int[size];
        int ptr = n-1;
        for(int i = size-1; i >= 0; i--) {
            while(idx[ptr] != i) ptr--;
            result[i] = arr[ptr--];
        }
        for(int r : result) {
            sb.append(r + " ");
        }
        System.out.print(sb);
    }
}

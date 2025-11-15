package algorithm_java.Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Baekjoon Online Judge 2467 용액
public class bj2467_v2025 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = n-1;
        int sIdx = start, eIdx = end;
        int min = arr[start] + arr[end];

        while(start < end) {
            int sum = arr[start] + arr[end];
            if(Math.abs(min) > Math.abs(sum)) {
                min = Math.abs(sum);
                sIdx = start;
                eIdx = end;
                if(sum == 0) break;
            }
            if (sum < 0) start++;
            else end--;
        }
        System.out.println(arr[sIdx] + " " + arr[eIdx]);
    }
}

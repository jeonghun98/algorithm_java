package algorithm_java.BOJ_SWEA_0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        long data[] = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) data[i] = Integer.parseInt(st.nextToken());

        int start = 0, end = n-1;
        int start_a = 0, end_a = n-1;
        long min = Integer.MAX_VALUE;

        while(start < end) {
            long sum = data[start] + data[end];
            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                start_a = start; end_a = end;
            }
            if(sum >= 0) end--;
            else start++;
        }

        System.out.println(data[start_a] + " " + data[end_a]);
    }
}

package algorithm_java.BOJ_0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int sum[][] = new int[n+1][n+1];

        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < n+1; j++) {
                int temp = Integer.parseInt(st.nextToken());
                sum[i][j] = temp + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1]; //누적합
            }
        }

        StringBuilder sb = new StringBuilder();
        int result;
        for(int i = 0; i < m; i++) {
            result = 0;
            st = new StringTokenizer(br.readLine());
            int start_x = Integer.parseInt(st.nextToken());
            int start_y = Integer.parseInt(st.nextToken());
            int end_x = Integer.parseInt(st.nextToken());
            int end_y = Integer.parseInt(st.nextToken());

            // 구간 합
            result = sum[end_x][end_y] + sum[start_x-1][start_y-1] - sum[end_x][start_y-1] - sum[start_x-1][end_y];
            sb.append(result + "\n");
        }
        System.out.println(sb.toString());
    }
}


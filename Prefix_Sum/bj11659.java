package algorithm_java.Prefix_Sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 11659 구간 합 구하기 4
public class bj11659 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int sum_data[] = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            sum_data[i+1] = temp + sum_data[i]; // 현재 데이터와 전 데이터들의 합 => 구간 합
        }

        StringBuilder sb = new StringBuilder();
        int result = 0;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            result = sum_data[end] - sum_data[start-1];	//start, end의 데이터의 차로 구하기
            sb.append(result + "\n");
        }
        System.out.println(sb.toString());
    }
}



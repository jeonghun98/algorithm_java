package algorithm_java.BOJ_SWEA_0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1806 부분합
public class bj1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int data[] = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            data[i] = Integer.parseInt(st.nextToken());

        System.out.println(min_length(n,s,data));
    }

    public static int min_length(int n, int s, int data[]) {
        int answer = Integer.MAX_VALUE;

        int start = 0, end = 0; // 두 포인터
        int partial_sum = 0; // 부분합

        while(end <= n) {
            if(partial_sum >= s) { // 합이 s이상이라면
                answer = Math.min(answer, end-start); // 길이 갱신(end-start : 현재 길이)
                partial_sum -= data[start++];   // 부분합 감소, start 지점 증가
            } else if(partial_sum < s) {
                partial_sum += data[end++];  // 부분합 증가, end 지점 증가
            }
        }
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}

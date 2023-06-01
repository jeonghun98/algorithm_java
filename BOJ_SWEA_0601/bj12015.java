package algorithm_java.BOJ_SWEA_0601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 12015 가장 긴 증가하는 부분 수열 2
public class bj12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

//        int dp[] = new int[n + 1]; // dp
        int dpNum[] = new int[n + 1]; // 해당 endIndex(dp)의 num
        int endIndex = 0;
//        int max = 0;

        Arrays.fill(dpNum, Integer.MAX_VALUE);
        dpNum[endIndex] = 0;

        for(int i = 1; i < n+1; i++) {
            int data = Integer.parseInt(st.nextToken());
            if(dpNum[endIndex] < data) { // 현재 dpNum의 마지막 숫자보다 크면 dpNum 추가
                dpNum[++endIndex] = data;
//                dp[i] = endIndex; // dp 갱신
            }
            else {
                int tmp = Arrays.binarySearch(dpNum, data); // 이분탐색
                if (tmp < 0) { // 갱신값이라면?
                    tmp = (-1) * (tmp + 1);
                    dpNum[tmp] = data; // 해당 index의 dpNum을 작은 숫자로 갱신
                }
//                dp[i] = tmp; // dp는 index의 값을 가져온다 -> dpNum의 endIndex == dp를 의미
            }
//            if(max < dp[i]) max = dp[i];
        }
        System.out.println(endIndex); // 현재 가르키는 index가 '가장 긴 증가하는 부분 수열'의 값
    }
}

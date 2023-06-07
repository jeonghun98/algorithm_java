package algorithm_java.BOJ_SWEA_0601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 14003 가장 긴 증가하는 부분 수열 5
public class bj14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int arr[] = new int[n+1];

        int dp[][] = new int[2][n + 1]; //해당 숫자의 arr index, dp
        int size = 0;
        int parent[] = new int[n + 1];

        Arrays.fill(dp[1], Integer.MAX_VALUE);
        dp[1][size] = Integer.MIN_VALUE;

        int maxIndex = 0;

        for(int i = 1; i < n+1; i++) {
            int data = Integer.parseInt(st.nextToken());
            arr[i] = data;
            if(dp[1][size] < data) { // 현재 dp의 마지막 숫자보다 크면 dp 추가
                parent[i] = dp[0][size];

                dp[1][++size] = data;
                dp[0][size] = i;

                maxIndex = i;

            } else {
                int tmp = Arrays.binarySearch(dp[1], data); // 이분탐색
                if (tmp < 0) { // 갱신값이라면
                    tmp = (-1) * (tmp + 1);
                    dp[1][tmp] = data; // 해당 index의 dp을 작은 숫자로 갱신
                }
                dp[0][tmp]= i;
                parent[i] = dp[0][tmp-1];
            }

        }
        System.out.println(size); // 현재 가르키는 size가 '가장 긴 증가하는 부분 수열'의 값

        int result[] = new int[size];
        for(int i = size-1; i >= 0; maxIndex = parent[maxIndex]) {
            result[i--] = arr[maxIndex];
        }
        for(int i = 0; i < size; i++) {
            System.out.print(result[i] + " ");
        }
    }
}

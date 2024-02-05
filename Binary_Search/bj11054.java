package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 11054 가장 긴 바이토닉 부분 수열
public class bj11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int arr[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());


        int dp[][] = new int[2][n];
        int LIS[][] = new int[2][n];
        int size1 = 0, size2 = 0;

        for(int i = 0; i < n; i++) {
            int tmp1 = Arrays.binarySearch(LIS[0], 0, size1, arr[i]);
            int tmp2 = Arrays.binarySearch(LIS[1], 0, size2, arr[n-i-1]);

            if(tmp1 < 0) tmp1 = Math.abs(tmp1)-1; // (tmp+1)*(-1)
            if(tmp2 < 0) tmp2 = Math.abs(tmp2)-1;

            // LIS
            LIS[0][tmp1] = arr[i];
            LIS[1][tmp2] = arr[n-i-1];

            if(size1 == tmp1) size1++;
            if(size2 == tmp2) size2++;

            // 현재 number의 LIS index
            dp[0][i] = tmp1+1;
            dp[1][n-i-1] = tmp2+1;

        }

        int answer = 0;
        for(int i = 0; i < n; i++) {
            // 앞에서 부터 증가 + 뒤에서 부터 증가 => 바이토닉 수열
            // 합하고 중복된 자기자신을 위해 -1을 함
            answer = Math.max(answer, dp[0][i] + dp[1][i] - 1);
        }

        System.out.println(answer);
    }
}

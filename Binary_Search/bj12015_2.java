package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj12015_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int dp[] = new int[n + 1];
        int size = 0;

        dp[size] = 0;

        for(int i = 1; i < n+1; i++) {
            int data = Integer.parseInt(st.nextToken());
            int tmp = Arrays.binarySearch(dp, 0,size, data); // 이분탐색
            if(tmp >= 0) continue;

            tmp = Math.abs(tmp)-1;
            dp[tmp] = data; // 해당 index의 dp을 작은 숫자로 갱신
            if(size == tmp) size++;
        }
        System.out.println(size); // 현재 가르키는 size가 '가장 긴 증가하는 부분 수열'의 값
    }
}
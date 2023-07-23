package algorithm_java.DP;
import java.util.*;
import java.io.*;

// Baekjoon Online Judge 2631 줄세우기
public class bj2631 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int dp[] = new int[n+1];
        int size = 0;
        dp[size] = 0;

        for(int i = 0; i < n; i++) {
            int data = Integer.parseInt(br.readLine());
            if(dp[size] < data) {
                dp[++size] = data;
            }else {
                int tmp = Arrays.binarySearch(dp, 0, size, data);
                if(tmp < 0) {
                    tmp = Math.abs(tmp)-1;
                    dp[tmp] = data;
                }
            }
        }
        System.out.println(n-size);
    }
}

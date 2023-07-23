package algorithm_java.Two_Pointer;
import java.util.*;
import java.io.*;

// Baekjoon Online Judge 1806 부분합
public class bj1806 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int []arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(twoPointer(n,s,arr));
    }
    public static int twoPointer(int n, int s, int arr[]) {
        int ans = Integer.MAX_VALUE;

        int start = 0, end = 0; // 두 포인터
        int partialSum = 0; // 부분합

        while(end <= n) {
            if(partialSum >= s) { // 합이 s이상이라면
                ans = Math.min(ans, end-start); // 길이 갱신(end-start : 현재 길이)
                partialSum -= arr[start++];   // 부분합 감소, start 지점 증가
            } else if(partialSum < s) {
                partialSum += arr[end++];  // 부분합 증가, end 지점 증가
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}

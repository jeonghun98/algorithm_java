package algorithm_java.Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 2470 두 용액
public class bj2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        long data[] = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            data[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(data); // 정렬
        int left = 0, right = n-1;
        int ml = 0, mr = n-1;
        long min = Integer.MAX_VALUE;

        while(left < right) { // 교차될 때까지 반복
            long sum = data[left] + data[right];
            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                ml = left; mr = right;
            }
            if(sum >= 0) right--; // sum 이 0보다 크다면 감소
            else left++;          // sum 이 0보다 작다면 감소
        }
        System.out.println(data[ml] + " " + data[mr]);
    }
}

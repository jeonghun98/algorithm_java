package algorithm_java.BOJ_SWEA_0430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 2473 세 용액
public class bj2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        long data[] = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            data[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(data);

        int ml = 0, mm = 1, mr = n-1;
        long min = Long.MAX_VALUE;

        // left, mid, right -> left는 0 ~ n-3까지 반복 & n-2 : mid, n-1 : right
        for(int i = 0; i < n-2; i++) {
            int left = i;
            int mid = i+1;
            int right = n-1;

            while(mid < right) {
                long sum = data[left] + data[mid] + data[right];
                if(min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    ml = left; mm = mid; mr = right;
                }
                if(sum > 0) right--;    // 0보다 크다면 감소
                else if(sum < 0) mid++; // 0보다 작다면 증가
                else {           // 0이라면 break
                    System.out.println(data[ml] + " " + data[mm] + " " + data[mr]);
                    return;
                }
            }
        }
        System.out.println(data[ml] + " " + data[mm] + " " + data[mr]);
    }
}

package algorithm_java.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Baekjoon Online Judge 2504 괄호의 값
public class bj2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] data = br.readLine().toCharArray();
        int cnt1 = 0, cnt2 = 0;
        int result = 0;
        int value = 1;

        // 분배법칙
        for(int i = 0; i < data.length; i++) {
            if(data[i] == '(') {
                cnt1++;
                value *= 2;
            }
            else if(data[i] == '[') {
                cnt2++;
                value *= 3;
            }
            else if(data[i] == ')') {
                if(--cnt1 < 0) break;

                if(data[i-1] == '(') result += value;
                value /= 2;
            }
            else if(data[i] == ']') {
                if(--cnt2 < 0) break;

                if(data[i-1] == '[') result += value;
                value /= 3;
            }
        }
        if(cnt1 != 0 || cnt2 != 0) System.out.println(0);
        else System.out.println(result);
    }
}

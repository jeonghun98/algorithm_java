package algorithm_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// Baekjoon Online Judge 1339 단어 수학
public class bj1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int alpha[] = new int[26];
        char word[][] = new char[n][];

        for(int i = 0; i < n; i++) {
            word[i] = br.readLine().toCharArray();
            int num = 1;
            for(int j = word[i].length-1; j >= 0; j--) {
                alpha[word[i][j]-'A'] += num;
                num *= 10;
            }
        }

        Arrays.sort(alpha);
//        System.out.println(Arrays.toString(alpha));

        int result = 0;
        int num = 9;
        for(int i = 25; i >= 0; i--) {
            if(alpha[i] == 0) break;
            result += alpha[i] * num;
            num--;
        }
        System.out.println(result);
    }
}

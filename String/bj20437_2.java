package algorithm_java.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// Baekjoon Online Judge 20437 문자열 게임 2
public class bj20437_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            String str = br.readLine();
            int len = str.length();
            int k = Integer.parseInt(br.readLine());

            int []alpha = new int[26];
            for(int i = 0; i < len; i++) {
                alpha[str.charAt(i)-'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < len; i++) {
                int cur = str.charAt(i);
                if(alpha[cur-'a'] < k)
                    continue;

                int cnt = 0;
                for(int j = i; j < len; j++) {
                    if(cur == str.charAt(j)) {
                        cnt++;
                    }
                    if(cnt == k) {
                        min = Math.min(min, j-i+1);
                        max = Math.max(max, j-i+1);
                        break;
                    }
                }
            }
            if(min == Integer.MAX_VALUE) {
                System.out.println(-1);
            }else {
                System.out.println(min + " " + max);
            }

        }
    }
}

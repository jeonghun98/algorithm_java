package algorithm_java.Sliding_Window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Baekjoon Online Judge 20437 문자열 게임 2
public class bj20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            String str = br.readLine();
            int len = str.length();
            int k = Integer.parseInt(br.readLine());

            ArrayList<Integer> alpha[] = new ArrayList[26];
            for(int i = 0; i < 26; i++)
                alpha[i] = new ArrayList<>();

            for(int i = 0; i < len; i++)
                alpha[str.charAt(i)-'a'].add(i); // index 저장

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < 26; i++) {
                if(alpha[i].size() < k)
                    continue;

                int l = 0;
                int r = k-1;
                while(r < alpha[i].size()) {
                    min = Math.min(min, alpha[i].get(r) - alpha[i].get(l) + 1);
                    max = Math.max(max, alpha[i].get(r) - alpha[i].get(l) + 1);
                    l++; r++;
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

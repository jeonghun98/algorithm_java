package algorithm_java.Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// Baekjoon Online Judge 5052 전화번호 목록
public class bj5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        Loop : for(int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());

            String phoneArray[] = new String[n];
            HashMap<String, Integer> map = new HashMap<>();

            for(int i = 0; i < n; i++) {
                String phone = br.readLine();
                map.put(phone, 1);
                phoneArray[i] = phone;
            }

            for(String phone : phoneArray) {
                for(int j = 1; j < phone.length(); j++) {
                    String tmp = phone.substring(0,j);
                    if(map.getOrDefault(tmp, 0) == 1) {
                        sb.append("NO" + "\n");
                        continue Loop;
                    }
                }
            }
            sb.append("YES" + "\n");
        }
        System.out.print(sb);
    }
}

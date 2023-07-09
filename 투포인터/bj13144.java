package algorithm_java.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int data[] = new int[n];
        for(int i = 0; i < n; i++)
            data[i] = Integer.parseInt(st.nextToken());

        boolean visited[] = new boolean[100_001];
        long cnt = 0;
        int start = 0, end = 0;
        while(start < n){
            while(end < n && !visited[data[end]]) {
                visited[data[end]] = true;
                end++;
            }
            cnt += end-start;;
            visited[data[start++]] = false;
        }
        System.out.println(cnt);
    }
}
package algorithm_java.Bitmasking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 1182 부분수열의 합
public class bj1182 {
    static int n, s;
    static int data[];
    public static int bitmasking() {

        int result = 0;
        for(int i = 0; i < 1 << n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += data[j];
                }
            }
            if (sum == s && i != 0)
                result++;
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        data = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(bitmasking());
    }
}
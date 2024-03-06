package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 10816 숫자 카드 2
public class bj10816 {
    static int n, m;
    static int data[];
    public static int lowerBound(int key) {
        int lo = 0;
        int hi = n;
        while(lo < hi) {
            int mid = lo + (hi-lo)/2;
            if(key <= data[mid]) hi = mid;
            else lo = mid+1;
        }
        return lo;
    }
    public static int upperBound(int key) {
        int lo = 0;
        int hi = n;
        while(lo < hi) {
            int mid = lo + (hi-lo)/2;
            if(key < data[mid]) hi = mid;
            else lo = mid+1;
        }
        return lo;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        data = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(data);
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            int tmp1 = lowerBound(num);
            int tmp2 = upperBound(num);
            sb.append(tmp2-tmp1 + " ");
        }
        System.out.print(sb);

    }
}

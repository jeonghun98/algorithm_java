package algorithm_java.Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 2143 두 배열의 합
public class bj2143 {
    public static int lowerBound(int n, int data[], int key) {
        int lo = 0, hi = n;
        while(lo < hi) {
            int mid = lo + (hi-lo)/2;
            if(key <= data[mid]) hi = mid;
            else lo = mid+1;
        }
        return lo;
    }
    public static int upperBound(int n, int data[], int key) {
        int lo = 0, hi = n;
        while(lo < hi) {
            int mid = lo + (hi-lo)/2;
            if(key < data[mid]) hi = mid;
            else lo = mid+1;
        }
        return lo;
    }
    public static void init(int n, int data[]) {
        int idx = n;
        int tmp;
        for(int i = 0; i < n-1; i++) {
            tmp = data[i];
            for(int j = i+1; j < n; j++) {
                tmp += data[j];
                data[idx++] = tmp;
            }
        }
        Arrays.sort(data);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int nLen = ((n+1) * n) / 2;
        int data1[] = new int[nLen];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n; i++) {
            data1[i] = Integer.parseInt(st.nextToken());
        }
        init(n, data1);

        int m = Integer.parseInt(br.readLine());
        int mLen = ((m+1) * m) / 2;
        int data2[] = new int[mLen];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < m; i++) {
            data2[i] = Integer.parseInt(st.nextToken());
        }
        init(m, data2);

        int idx = 0;
        long result = 0;

        while(idx < nLen) {
            int tmp1 = lowerBound(mLen, data2, t-data1[idx]);
            int tmp2 = upperBound(mLen, data2, t-data1[idx]);
            result += (tmp2-tmp1);
            idx++;
        }

        System.out.println(result);
    }
}

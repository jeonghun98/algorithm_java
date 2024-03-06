package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 2110 공유기 설치
public class bj2110 {
    static int n, c;
    static int data[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        data = new int[n];
        for(int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(data);
        System.out.println(binarySearch());
    }
    public static int binarySearch() { //upper bound
        int lo = 1;
        int hi = data[n-1] - data[0] + 1;
        while(lo < hi) {
            int mid = lo + (hi-lo)/2;
            if(countWifi(mid) < c) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }
        return lo-1;
    }
    public static int countWifi(int mid) {
        int cnt = 1;
        int lastHome = data[0];
        for(int i = 1; i < n; i++) {
            if(data[i] - lastHome >= mid) {
                cnt++;
                lastHome = data[i];
            }
        }
        return cnt;
    }
}

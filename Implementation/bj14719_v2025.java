package algorithm_java.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;
// Baekjoon Online Judge 14719 빗물
public class bj14719_v2025 {
    static int h,w;
    static int[] arr;
    static int water;
    public static void getRain(int maxH) {
        int minus = maxH - arr[0];

        int tmp = arr[0];
        for(int i = 0; i < w; i++) {
            if(tmp < arr[i]) {
                minus = maxH - arr[i];
                tmp = arr[i];
            }
            water -= minus;
        }

        minus = maxH - arr[w-1];
        tmp = arr[w-1];
        for(int i = w-1; i >= 0; i--) {
            if(tmp < arr[i]) {
                minus = maxH - arr[i];
                tmp = arr[i];
            }
            water -= minus;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        arr = new int[w];

        int maxH = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            water += (h-arr[i]);
            maxH = Math.max(arr[i], maxH);
        }

        water -= (h-maxH) * w;
        getRain(maxH);
        System.out.println(water);
    }

}

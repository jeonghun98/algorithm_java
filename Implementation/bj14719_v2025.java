package algorithm_java.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Baekjoon Online Judge 14719 빗물
public class bj14719_v2025 {
    public static int getWater(int h, int w, int maxH, int water, int[] arr) {
        int maxH2 = arr[0];
        for(int i = 0; i < w; i++) {
            if(maxH2 < arr[i]) {
                maxH2 = arr[i];
            }
            water -= (maxH - maxH2);
            if(maxH2 == maxH) break;
        }

        maxH2 = arr[w-1];
        for(int i = w-1; i >= 0; i--) {
            if(maxH2 < arr[i]) {
                maxH2 = arr[i];
            }
            water -= (maxH - maxH2);
            if(maxH2 == maxH) break;
        }
        return water;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] arr = new int[w];
        int maxH = -1, block = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxH = Math.max(maxH, arr[i]);
            block += arr[i];
        }
        System.out.println(getWater(h, w, maxH, h*w - block - ((h-maxH) * w), arr));
    }

}

package algorithm_java.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 14658 하늘에서 별똥별이 빗발친다
public class bj14658 {
    static int n, m, l, k;
    static Star[] stars;
    static class Star implements Comparable<Star> {
        int x, y;
        Star(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Star o) {
            if(this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }
    public static int cntStart(Star s) {
        int cnt = 0;
        for(Star now : stars) {
            if(s.x <= now.x && now.x <= s.x + l && s.y <= now.y && now.y <= s.y + l) cnt++;
        }
        return cnt;
    }
    public static int findMaxBounceOff() {
        int maxStart = 0;
        Arrays.sort(stars);
        for(int i = 0; i < k; i++) {
            for(int j = 0; j < k; j++) {
                int x = stars[i].x;
                int y = stars[j].y;
                maxStart = Math.max(maxStart, cntStart(new Star(x, y)));
            }
        }
        return maxStart;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        stars = new Star[k];
        for(int i = 0 ; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars[i] = new Star(x, y);
        }
        System.out.println(k-findMaxBounceOff());
    }
}

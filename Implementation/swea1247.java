package algorithm_java.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea1247 {
    static int n;
    static pos start, end; // 회사, 집
    static pos[] customer;	// 고객 위치
    static int result;
    static boolean isSelected[]; // 방문한 고객 위치

    public static class pos{	// 위치 class
        int x,y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int distance(pos p1, pos p2) {	// 거리 계산
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public static void find(pos p, int cnt, int dis) {
        if(dis > result) return;	// 현재 min값보다 dis가 크다면 return

        if(cnt == n) {	// n개의 고객한테 방문했다면 집까지 위치 계산
            result = Math.min(result, dis + distance(p,end));
            return;
        }

        for(int i = 0; i < n; i++) {	// 순열 재귀함수
            if(isSelected[i]) continue;
            isSelected[i] = true;
            find(customer[i], cnt+1, dis + distance(p,customer[i]));
            isSelected[i] = false;
        }
    }

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            start = new pos(x,y);			// 회사

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            end = new pos(x,y);			// 집

            isSelected = new boolean[n];	// 방문한 고객 위치 확인
            customer = new pos[n];	// 고객
            for(int i = 0; i < n; i++) {
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                customer[i] = new pos(x,y);
            }

            result = Integer.MAX_VALUE;
            find(start, 0,0);	//재귀 함수
            sb.append("#" + tc + " " + result + "\n");
        }
        System.out.println(sb.toString());

    }

}

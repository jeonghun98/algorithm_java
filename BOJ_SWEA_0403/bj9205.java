package algorithm_java.BOJ_SWEA_0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj9205 {
    static int n;
    static class Pos{
        int x, y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Pos> q;
    static Pos[] arr; // 편의점 배열
    static boolean[] visited; // 편의점 방문 체크

    public static boolean bfs(int e_x, int e_y) {
        boolean find = false;

        while(!q.isEmpty()) {
            Pos now = q.poll();
            int dis = Math.abs(now.x - e_x) + Math.abs(now.y - e_y);

            if(dis <= 1000) {
                find = true;
                break;
            }

            for(int i = 0; i < n; i++) {
                dis = Math.abs(now.x - arr[i].x) + Math.abs(now.y - arr[i].y);
                if(dis <= 1000 && !visited[i]) {

                    q.add(new Pos(arr[i].x, arr[i].y));
                    visited[i] = true;
                }
            }

        }
        return find;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            q = new ArrayDeque<>();
            arr = new Pos[n];

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            q.add(new Pos(x,y));

            visited = new boolean[n];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                arr[i] = new Pos(x,y);
            }

            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            System.out.println(bfs(x,y) == true ? "happy" : "sad");

        }
    }
}

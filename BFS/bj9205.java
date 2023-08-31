package algorithm_java.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// Baekjoon Online Judge 9205 맥주 마시면서 걸어가기
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
            visited = new boolean[n];
            int x = 0, y = 0;
            for(int i = 0; i < n+2; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                if(i == 0) q.add(new Pos(x,y));
                else if(i < n+1) arr[i-1] = new Pos(x,y);
            }

            System.out.println(bfs(x,y) == true ? "happy" : "sad");

        }
    }
}

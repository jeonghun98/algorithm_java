package algorithm_java.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 2146 다리 만들기
public class bj2146 {
    static int n;
    static int data[][];
    static boolean visited[][];
    static Queue<Pos> q;
    public static class Pos {
        int x, y, cnt, idx;
        Pos(int x, int y) {
            this(x, y, 0, 0);
        }
        Pos(int x, int y, int idx, int cnt) {
            this.x = x;
            this.y = y;
            this.idx = idx;
            this.cnt = cnt;
        }
    }
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,-1,0,1};
    public static void init(Pos p, int idx) {
        Queue<Pos> initQ = new ArrayDeque<>();

        initQ.add(p);
        q.add(new Pos(p.x, p.y, idx, 0));
        visited[p.x][p.y] = true;
        data[p.x][p.y] = idx;

        while(!initQ.isEmpty()) {
            Pos cur = initQ.poll();
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || data[nx][ny] == 0) continue;
                initQ.add(new Pos(nx, ny));
                q.add(new Pos(nx, ny, idx, 0));
                data[nx][ny] = idx;
                visited[nx][ny] = true;

            }
        }

    }
    public static boolean find(Pos p) {
        for(int i = 0; i < 4; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || data[nx][ny] == 0) continue;
            if(data[nx][ny] != p.idx) return true;
        }
        return false;
    }
    public static int bridge(){
        while(!q.isEmpty()) {
            Pos cur = q.poll();
            if(find(cur)) return cur.cnt;

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || data[nx][ny] != 0) continue;
                q.add(new Pos(nx, ny, cur.idx, cur.cnt+1));
                visited[nx][ny] = true;

            }
        }
        return Integer.MAX_VALUE;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        data = new int[n][n];
        visited = new boolean[n][n];
        q = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        int idx = 1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(data[i][j] == 1) {
                    init(new Pos(i,j), ++idx);
                    answer = Math.min(answer, bridge());

                    q.clear();
                    for(int k = 0; k < n; k++) {
                        Arrays.fill(visited[k], false);
                    }
                }
            }
        }

        System.out.println(answer);
    }

}
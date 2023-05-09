package algorithm_java.BOJ_SWEA_0509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj16946 {
    static int arr[][];
    static int cnt[][];
    static boolean visited[][];
    static int n, m, count;
    static Deque<Pos> q;
    static class Pos {
        int x,y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};

    public static void print() { // 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(cnt[i][j]);
            }sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void move() { // 0을 중심으로 count 하고 근접 벽에 +count
        visited = new boolean[n][m];
        q = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(arr[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    count = 1;
                    dfs(new Pos(i, j));
                    bfs();
                }
            }
        }
        print();
    }

    public static void bfs() { // 현재 count 한 0을 1에 더해주기
        while(!q.isEmpty()) {
            Pos now = q.poll();
            cnt[now.x][now.y] = (cnt[now.x][now.y] + count) % 10;
            visited[now.x][now.y] = false;
        }
    }

    public static void dfs(Pos now) { // 방문하지 않은 0을 중심으로 dfs, 1이면 q에 넣기
        for(int i = 0; i < 4; i++) {
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;

            if(arr[nx][ny] == 1) {
                q.add(new Pos(nx, ny));
                visited[nx][ny] = true;
                continue;
            }
            visited[nx][ny] = true;
            count++;
            dfs(new Pos(nx,ny));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        cnt = new int[n][m];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                if(str.charAt(j) == '1') {
                    arr[i][j] = cnt[i][j] = 1;
                }
            }
        }
        move();
    }
}
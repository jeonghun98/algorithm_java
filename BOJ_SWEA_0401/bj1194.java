package algorithm_java.BOJ_SWEA_0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1194 {
    static int n, m;
    static char arr[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};

    static class Pos{
        int x, y, keys;
        public Pos(int x, int y, int keys) {
            super();
            this.x = x;
            this.y = y;
            this.keys = keys;
        }
    }

    public static int moon(Pos start) {
        Queue<Pos> q = new ArrayDeque<>();
        q.add(start);

        int dp[][][] = new int[n][m][(1<<7)+1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        dp[start.x][start.y][0] = 0;

        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(arr[now.x][now.y] == '1') // 탈출
                return dp[now.x][now.y][now.keys];

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || arr[nx][ny] == '#') continue; // 벽

                if('A' <= arr[nx][ny] && arr[nx][ny] <= 'F') {// 문
                    if(dp[nx][ny][now.keys] > dp[now.x][now.y][now.keys] + 1) {
                        if(bitmasking(now.keys, arr[nx][ny]-'A')) { //현재 갖고 있는 키로 열 수 있다면
                            q.add(new Pos(nx,ny,now.keys));
                            dp[nx][ny][now.keys] = dp[now.x][now.y][now.keys] + 1;
                        }
                    }
                    continue;
                }
                if('a' <= arr[nx][ny] && arr[nx][ny] <= 'f') {// 열쇠
                    int key = (1 << (arr[nx][ny]-'a'));

                    if(dp[nx][ny][now.keys | key] > dp[now.x][now.y][now.keys] + 1) { // 수집된 키 update
                        q.add(new Pos(nx,ny,now.keys | key));
                        dp[nx][ny][now.keys | key] = dp[now.x][now.y][now.keys] + 1;
                    }
                    continue;
                }

                //빈칸, 출구
                if(dp[nx][ny][now.keys] > dp[now.x][now.y][now.keys] + 1) {
                    q.add(new Pos(nx,ny,now.keys));
                    dp[nx][ny][now.keys] = dp[now.x][now.y][now.keys] + 1;
                }
            }
        }

        return -1;
    }

    public static boolean bitmasking(int key, int door) { // 갖고 있는 키로 문을 열 수 있는지 확인
        if((key & (1 << (door))) != 0)
            return true;
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        Pos start = null;
        for(int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
            for(int j = 0; j < m; j++) {
                if(arr[i][j] == '0') start = new Pos(i,j,0);
            }
        }

        System.out.println(moon(start));

    }

}

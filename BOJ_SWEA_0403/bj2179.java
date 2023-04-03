package algorithm_java.BOJ_SWEA_0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2179 {
    static int n,m, data[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};

    static class Pos{
        int x,y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void bfs(int x, int y) {
        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(x,y));
        int nx = 0, ny = 0;

        while(!q.isEmpty()){
            Pos now = q.poll();
            if(now.x == n-1 && now.y == m-1) {
                System.out.println(data[n-1][m-1]);
                return;
            }
            for(int i = 0; i < 4; i++) {
                nx = now.x + dx[i];
                ny = now.y + dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && data[nx][ny] == 1) {
                    data[nx][ny] = data[now.x][now.y] + 1;
                    q.add(new Pos(nx, ny));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data = new int[n][m];

        for(int i = 0; i < n; i++) {
            char tmp[] = br.readLine().toCharArray();
            for(int j = 0; j <m; j++) {
                data[i][j] = tmp[j]-'0';
            }
        }
        bfs(0,0);
    }
}

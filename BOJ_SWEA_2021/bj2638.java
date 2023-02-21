package algorithm_java.BOJ_SWEA_2021;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2638 {
    static int n,m;
    static int data[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static Queue<position> q;
    static int result = 0;
    static int air_index;

    public static class position {
        int x, y, time;

        public position(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void air(int x, int y) {
        if(data[x][y] == 1) return;
        data[x][y] = air_index;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m || data[nx][ny] == 1 || data[nx][ny] == air_index)
                continue;
            air(nx,ny);
        }
    }

    public static void cheese() {

        int time = 0;
        while(!q.isEmpty()) {
            position p = q.poll();
            if(time != p.time) {
                air_index++; air(0,0);
                time = p.time;
            }

            int side = 0;
            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || data[nx][ny] == 0 || data[nx][ny] == 1)
                    continue;
                side++;
            }

            if(side < 2)
                q.offer(new position(p.x,p.y,p.time+1));
            else
                data[p.x][p.y] = 0;

            result = time+1;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new int [n][m];
        q = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
                if(data[i][j] == 1)
                    q.offer(new position(i,j,0));
            }
        }

        air_index = 2;
        air(0,0);
        cheese();
        System.out.println(result);
    }
}

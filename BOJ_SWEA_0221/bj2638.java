package algorithm_java.BOJ_SWEA_0221;

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

        public position(int x, int y, int time) { // 위치, 시간
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void air(int x, int y) { // 외부공기 초기화 dfs 메소드
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

    public static void cheese() { //치즈 녹이기

        int time = 0;
        while(!q.isEmpty()) { // 큐에 치즈 조각이 있다면 반복
            position p = q.poll();
            if(time != p.time) {    // 저장된 시간보다 시간이 증가했다면 외부공기도 초기화
                air_index++; air(0,0);
                time = p.time;
            }

            int side = 0;
            for(int i = 0; i < 4; i++) { // 사방탐색
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || data[nx][ny] == 0 || data[nx][ny] == 1)
                    continue;
                side++; //외부와 접촉 count
            }

            if(side < 2)    // 외부 접촉이 2변 이하라면 다시 큐에 넣기
                q.offer(new position(p.x,p.y,p.time+1));
            else
                data[p.x][p.y] = 0; // 접촉이 2변 이상이라면 0으로 변경(치즈 녹이기)

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
                    q.offer(new position(i,j,0)); //치즈있는 위치 -> 큐에 넣기
            }
        }

        air_index = 2; //외부공기
        air(0,0); // 외부공기 값 변경
        cheese();   // 치즈 녹이기
        System.out.println(result);
    }
}

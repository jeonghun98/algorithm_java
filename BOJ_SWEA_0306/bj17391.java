package algorithm_java.BOJ_SWEA_0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj17391 {
    static int n,m;

    static int data[][]; // 부스터 n x m
    static int dis[][]; // 최소 방문 count

    static int dx[] = {1,0}; //아래, 오른쪽
    static int dy[] = {0,1};

    public static class Pos{ //위치
        int x, y;
        public Pos(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
    public static int bfs() { //격자 방문 cnt 최소 찾기
        Queue <Pos> q = new ArrayDeque<>();
        q.add(new Pos(0,0));
        dis[0][0] = 0; //처음은 cnt = 0

        while(!q.isEmpty()) {
            Pos now = q.poll();

            for(int i = 0; i < 2; i++) { // 아래와 오른쪽 탐색
                int nx = now.x;
                int ny = now.y;

                for(int j = 0;j < data[now.x][now.y]; j++) { // 해당 위치에서 얻은 부스터만큼 움직이기
                    nx += dx[i];
                    ny += dy[i];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= m) break; // 더 이상 못 간다면 break

                    if(dis[nx][ny] > dis[now.x][now.y] + 1) { // 방문할 예정인 곳의 최소 방문 cnt > 현재 경로를 방문해서 가는 cnt => 갱신
                        dis[nx][ny] = dis[now.x][now.y] + 1; // 현재 cnt + 1 (최소 방문 cnt)
                        q.add(new Pos(nx,ny));	// q에 방문 넣어주기
                    }
                }
            }
        }

        return dis[n-1][m-1]; // 오른쪽 하단 n,m위치의 최소 방문 cnt를 return
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new int[n][m]; // 부스터 n x m
        dis = new int[n][m]; // 최소 방문 count
        for(int i = 0; i < n; i++)
            Arrays.fill(dis[i], Integer.MAX_VALUE); // max로 초기화

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                data[i][j] = Integer.parseInt(st.nextToken()); // 입력
            }
        }

        System.out.println(bfs()); // 출력
    }

}

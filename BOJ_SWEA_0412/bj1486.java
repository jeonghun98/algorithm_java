package algorithm_java.BOJ_SWEA_0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1486 { // BJ 1486 등산
    static int N,M,T,D;
    static final int INF = Integer.MAX_VALUE;
    static int map[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    public static class Pos {
        int x, y, cost;
        public Pos(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    public static int altitude(char c) {
        if(c <= 'Z') return c-'A';
        else return c-'a'+26;
    }
    public static int[][] dijkstra(boolean rise){
        int dis[][] = new int[N][M];
        for(int i = 0; i < N; i++)
            Arrays.fill(dis[i], INF);
        dis[0][0] = 0;

        PriorityQueue<Pos> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Pos(0,0,0));

        while(!pq.isEmpty()) {
            Pos now = pq.poll();
            int now_h = map[now.x][now.y];

            if(dis[now.x][now.y] < now.cost) continue;

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                int next_h = map[nx][ny];
                if(Math.abs(next_h - now_h) > T) continue;

                int cost = 0;
                if(rise) { // 등산
                    if(next_h > now_h) cost = (next_h-now_h) * (next_h-now_h) + now.cost;
                    else cost = 1 + now.cost;
                }
                else { // 하산
                    if(next_h < now_h) cost = (next_h-now_h) * (next_h-now_h) + now.cost;
                    else cost = 1 + now.cost;
                }

                if(dis[nx][ny] > cost) {
                    dis[nx][ny] = cost;
                    pq.add(new Pos(nx, ny, cost));
                }
            }
        }
//        print(dis);
        return dis;
    }

    public static int max_height(int dis1[][], int dis2[][]){
        int max = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(dis1[i][j] != INF && dis2[i][j] != INF && dis1[i][j] + dis2[i][j] <= D) {
                    max = Math.max(max, map[i][j]); // 등산 + 하산 <= D 시간이면 update
                }
            }
        }
        return max;
    }

    public static void print(int dis[][]) {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dis[i][j] == INF) System.out.print("-1" + " ");
                else System.out.print(dis[i][j]  + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            char tmp[] = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                map[i][j] = altitude(tmp[j]); // 문자 -> 높이
            }
        }

        System.out.println(max_height(dijkstra(true), dijkstra(false)));
    }
}

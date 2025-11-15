package algorithm_java.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Baekjoon Online Judge 4485 녹색 옷 입은 애가 젤다지?
public class bj4485_v2025 {
    static int n;
    static int[][] arr;
    public static class Pos {
        int x, y, sum;
        Pos(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
    }
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static int bfs() {
        int[][] visited = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(visited[i], 125*2*10);

        PriorityQueue<Pos> q = new PriorityQueue<>((o1, o2) -> o1.sum - o2.sum);
        q.add(new Pos(0, 0, arr[0][0]));
        visited[0][0] = arr[0][0];

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                int sum = cur.sum + arr[nx][ny];
                if(sum < visited[nx][ny]) {
                    visited[nx][ny] = sum;
                    q.add(new Pos(nx, ny, sum));
                }
            }
        }
        return visited[n-1][n-1];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int idx = 1;
        while(true) {
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            arr = new int[n][n];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem ").append(idx++).append(": ").append(bfs()).append("\n");
        }
        System.out.print(sb);
    }
}

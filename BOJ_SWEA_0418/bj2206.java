package algorithm_java.BOJ_SWEA_0418;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Baekjoon Online Judge 2206 벽 부수고 이동하기
public class bj2206 {
    static int n,m;
    static int arr[][];

    public static class Pos{
        int x, y, wall, cnt;
        public Pos(int x, int y, int cnt, int wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.cnt = cnt;
        }
    }
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    public static int dijkstra() {
        boolean visited[][][] = new boolean[n][m][2];
        visited[0][0][1] = true;

        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(0,0,1,1));

        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(now.x == n-1 && now.y == m-1)
                return now.cnt;

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(arr[nx][ny] == 1) { // 벽 O
                    if(!visited[nx][ny][0] && now.wall == 1) { // 방문 체크 + 벽을 부실 수 있는 횟수 체크
                        visited[nx][ny][0] = true;
                        q.add(new Pos(nx,ny, now.cnt+1,0));
                    }
                }
                else { // 벽 X
                    if(!visited[nx][ny][now.wall]) { // 방문 체크
                        visited[nx][ny][now.wall] = true;
                        q.add(new Pos(nx,ny, now.cnt+1,now.wall));
                    }
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for(int i = 0; i < n; i++) {
            char tmp[] = br.readLine().toCharArray();
            for(int j = 0; j < m; j++) {
                arr[i][j] = tmp[j] - '0';
            }
        }
        System.out.println(dijkstra());
    }
}

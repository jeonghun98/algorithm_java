package algorithm_java.Implementation;
import java.io.*;
import java.util.*;

// Softeer 6246 [HSAT 7회 정기 코딩 인증평가 기출] 순서대로 방문하기
public class soft6246 {
    static int n, m;
    static int data[][];
    static Pos point[];
    static class Pos {
        int x, y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static int cnt = 0;
    public static void dfs(Pos start, boolean visited[][], int idx) {
        if(idx == m) {
            cnt++;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n || data[nx][ny] == 1 || visited[nx][ny]) continue;
            if(point[idx].x == nx && point[idx].y == ny) {
                visited[start.x][start.y] = true;
                dfs(new Pos(nx, ny), visited, idx+1);
                visited[start.x][start.y] = false;
            } else {
                visited[start.x][start.y] = true;
                dfs(new Pos(nx, ny), visited, idx);
                visited[start.x][start.y] = false;
            }

        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        point = new Pos[m];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1 ;
            int y = Integer.parseInt(st.nextToken())-1;
            point[i] = new Pos(x,y);
        }
        boolean visited[][] = new boolean[n][n];
        visited[point[0].x][point[0].y] = true;
        dfs(point[0], visited, 1);
        System.out.println(cnt);
    }
}
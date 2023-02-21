package algorithm_java.BOJ_SWEA_2021;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bj10026 {
    static int n;
    static char data[][];
    static boolean check[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};

    public static void dfs1(int x, int y, char RGB) { // 일반 색칠 구분
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n || check[nx][ny] || RGB != data[nx][ny])
                continue;
            check[nx][ny] = true;
            dfs1(nx,ny,RGB);
        }
    }

    public static void dfs2(int x, int y, char RGB) {	// 적록색약 색칠 구분
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n || check[nx][ny])
                continue;

            if(RGB == 'B') { //파랑
                if(data[nx][ny] == 'B') {
                    check[nx][ny] = true;
                    dfs2(nx,ny,RGB);
                }
            }
            else {	// 빨강 + 초록
                if(data[nx][ny] == 'R' || data[nx][ny] == 'G') {
                    check[nx][ny] = true;
                    dfs2(nx,ny,RGB);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        data = new char[n][n];
        int result = 0;

        check = new boolean[n][n];	// 방문 체크용 nxn
        for(int i = 0; i < n; i++) {
            data[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n;j++) {
                if(!check[i][j]) {
                    dfs1(i,j,data[i][j]);
                    result++;
                }
            }
        }
        sb.append(result + " ");

        check = new boolean[n][n]; result = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n;j++) {
                if(!check[i][j]) {
                    dfs2(i,j,data[i][j]);
                    result++;
                }
            }
        }
        sb.append(result);
        System.out.println(sb.toString());
    }
}


package algorithm_java.BOJ_SWEA_0221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class bj3109 {
    static int r, c;
    static char data[][];

    static int dx[] = {-1,0,1};	// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
    static int dy[] = {1,1,1};

    static int result; // 파이프라인 count
    static boolean flag;	// 왼쪽 열에 도착한지 확인하는 flag

    public static class position {
        int x, y;
        public position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void pipeline(int x, int y) {	//dfs
        if(y == c-1) {	// 왼쪽 도착하면 결과값 + 1, flag = true
            result++; flag= true;
            return;
        }
        for(int i = 0; i < 3; i++) {	// 오른쪽으로 갈 수 있는 세곳 방문
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= r || ny < 0 || ny >= c || data[nx][ny] == 'x' || data[nx][ny] == ',')
                continue;

            data[nx][ny] = ',';	// 방문할 수 있다면 방문체크 후 재귀함수 호출
            pipeline(nx,ny);

            if(flag) return;	//왼쪽 열에 도착했다면 더 이상 진행하지 않음
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        data = new char[r][c];
        for(int i = 0; i < r; i++) {
            data[i] = br.readLine().toCharArray();
        }

        result = 0; flag = false;
        for(int row = 0; row < r; row++) { //왼쪽 r개의 행을 모두 dfs를 돌린다.
            flag = false;
            pipeline(row,0);
        }
        System.out.println(result);
    }
}

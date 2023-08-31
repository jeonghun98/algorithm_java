package algorithm_java.BFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// Baekjoon Online Judge 7569 토마토
public class bj7569 {
    static int n,m,h;
    static int tomato[][][];
    static Queue<pos> q;

    static class pos {
        int x, y, h, time;
        public pos(int x, int y, int h, int time) { // 좌표 + 시간
            super();
            this.x = x;
            this.y = y;
            this.h = h;
            this.time = time;
        }
    }

    public static boolean check_tomato() { // 익지 않은 토마토 있는지 확인하는 메소드
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < h; k++) {
                    if(tomato[i][j][k] == 0) return false;
                }
            }
        }
        return true;
    }

    public static int bfs() { //bfs
        int result = -1;
        int dx[] = {0,0,0,0,-1,1}; //상하 좌우 앞뒤
        int dy[] = {0,0,-1,1,0,0};
        int dh[] = {1,-1,0,0,0,0};

        while(!q.isEmpty()) {
            pos now = q.poll();
            result = now.time;

            for(int i = 0; i < 6; i++) { // 상하 좌우 앞뒤를 모두 탐색
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nh = now.h + dh[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || nh < 0 || nh >= h ||tomato[nx][ny][nh] != 0)
                    continue;
                q.offer(new pos(nx,ny,nh, now.time+1)); // 익지 않은 토마토 -> q에 넣기
                tomato[nx][ny][nh] = 1;	// 익지 않은 토마토 익히기
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        tomato = new int[n][m][h]; // 토마토 배열
        q = new ArrayDeque<>();

        for(int k = 0; k < h; k++) {
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < m; j++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    if(tomato[i][j][k] == 1)
                        q.offer(new pos(i,j,k,0)); //익은 토마토는 q에 넣는다.
                }
            }
        }

        int result = bfs(); //익지 않은 토마토가 없다면 result , 아니라면 -1
        System.out.println(check_tomato()== true ? result : -1);

    }

}


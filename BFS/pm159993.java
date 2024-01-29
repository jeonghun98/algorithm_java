package algorithm_java.BFS;
import java.util.*;

// programmers 159993 미로 탈출
public class pm159993 {
    static class Pos {
        int x, y, t;
        boolean o;
        Pos (int x, int y, int t, boolean o) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.o = o;
        }
        Pos (int x, int y) {
            this(x, y, 0, false);
        }
    }
    static int n, m;
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};

    public static int bfs(Pos start, char[][] maps) {
        Queue<Pos> q = new ArrayDeque<>();
        boolean visited[][][] = new boolean[n][m][2];

        q.add(start);
        visited[start.x][start.y][0] = true;

        while(!q.isEmpty()) {
            Pos cur = q.poll();
            // 출구면서 레버를 당겼다면 time return
            if(maps[cur.x][cur.y] == 'E' && cur.o)
                return cur.t;

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                // 범위 체크, 벽 체크, 방문 체크
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || maps[nx][ny] == 'X'||
                        visited[nx][ny][cur.o ? 1 : 0]) continue;

                if(maps[nx][ny] == 'L') {
                    visited[nx][ny][1] = true;
                    q.add(new Pos(nx, ny, cur.t + 1, true));
                } else {
                    visited[nx][ny][cur.o ? 1 : 0] = true;
                    q.add(new Pos(nx, ny, cur.t + 1, cur.o));
                }
            }
        }
        return -1;
    }
    public static int solution(String[] maps) {
        int answer = -1;

        n = maps.length;
        m = maps[0].length();
        char map[][] = new char[n][];

        for(int i = 0; i < n; i++)
            map[i] = maps[i].toCharArray();


        Loop : for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 'S') {
                    answer = bfs(new Pos(i,j), map);
                    break Loop;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[] {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"})); // 16
        System.out.println(solution(new String[] {"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"})); // -1
    }
}

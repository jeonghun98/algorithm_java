package algorithm_java.BFS;
import java.util.*;

// programmers 169199 리코쳇 로봇
public class pm169199 {
    public static class Pos {
        int x, y, cnt;
        Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
        Pos(int x, int y) {
            this(x, y, 0);
        }
    }
    static int dx[] = {0,-1,0,1};
    static int dy[] = {1,0,-1,0};
    static int n, m;

    public static int bfs(Pos start, char[][] board) {
        Queue<Pos> q = new ArrayDeque<>();
        q.add(start);

        boolean visited[][] = new boolean[n][m];
        visited[start.x][start.y] = true;

        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(board[now.x][now.y] == 'G') return now.cnt;

            for(int i = 0; i < 4; i++) {
                int nx = now.x;
                int ny = now.y;
                for(int j = 0; j < Math.max(n, m); j++) {
                    nx += dx[i];
                    ny += dy[i];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] == 'D') {
                        nx -= dx[i];
                        ny -= dy[i];
                        break;
                    };
                }

                if(!visited[nx][ny]) {
                    q.add(new Pos(nx, ny, now.cnt+1));
                    visited[nx][ny] = true;
                }
            }
        } return -1;

    }
    public static int solution(String[] board) {
        n = board.length;
        m = board[0].toCharArray().length;

        char[][] b = new char[n][];
        for(int i = 0; i < n; i++) {
            b[i] = board[i].toCharArray();
        }

        Pos start = new Pos(0,0);
        Loop1 : for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(b[i][j] == 'R') {
                    start = new Pos(i, j);
                    break Loop1;
                }
            }
        }
        return bfs(start, b);
    }
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."})); // 7
        System.out.println(solution(new String[] {".D.R", "....", ".G..", "...D"}));                        //  -1
    }
}

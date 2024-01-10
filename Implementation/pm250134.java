package algorithm_java.Implementation;

// programmers 250134 [PCCP 기출문제] 4번 / 수레 움직이기
public class pm250134 {
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static int n, m;
    static int result;
    public static class Pos {
        int x, y, c;
        boolean e;
        Pos(int x, int y, int c, boolean e) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.e = e;
        }
    }
    // 1->3, 2->4
    public static boolean check(Pos[] wagon, int[][] maze) {
        boolean flag = true;
        for(int i = 0; i < 2; i++) {
            if(maze[wagon[i].x][wagon[i].y] != wagon[i].c+2) flag = false;
            else wagon[i].e = true;
        }
        return flag;
    }
    public static boolean pass(int nx, int ny, int c, int[][] maze, boolean [][] redV, boolean[][] blueV) {
        if(nx < 0 || nx >= n || ny < 0 || ny >= m || maze[nx][ny] == 5) return false;
        if((c == 1 && redV[nx][ny]) || (c == 2 && blueV[nx][ny])) return false;
        return true;
    }
    public static void dfs(Pos red, Pos blue, int cnt, int[][] maze, boolean [][] redV, boolean[][] blueV) {

        if(result < cnt)
            return;

        if(check(new Pos[] {red, blue}, maze)) {
            result = Math.min(result, cnt);
            return;
        }

        redV[red.x][red.y] = true;
        blueV[blue.x][blue.y] = true;

        Pos nRed, nBlue;
        for(int i = 0; i < 4; i++) {
            int nx = red.x + dx[i];
            int ny = red.y + dy[i];

            // 수레가 도착했거나, 갈 수 없으면 움직인다
            if(red.e) nRed = new Pos(red.x, red.y, red.c, red.e);
            else if(!pass(nx, ny, red.c, maze, redV, blueV)) continue;
            else nRed = new Pos(nx, ny, red.c, red.e);

            for(int j = 0; j < 4; j++) {
                nx = blue.x + dx[j];
                ny = blue.y + dy[j];

                if(blue.e) nBlue = new Pos(blue.x, blue.y, blue.c, blue.e);
                else if(!pass(nx, ny, blue.c, maze, redV, blueV)) continue;
                else nBlue = new Pos(nx, ny, blue.c, blue.e);

                // 같은 곳 이동 x
                if(nRed.x == nBlue.x && nRed.y == nBlue.y) continue;
                // 교환 x
                if(nRed.x == blue.x && nRed.y == blue.y &&
                        nBlue.x == red.x && nBlue.y == red.y) continue;

                dfs(nRed, nBlue, cnt+1, maze, redV, blueV);
            }
        }

        redV[red.x][red.y] = false;
        blueV[blue.x][blue.y] = false;

    }
    public static int solution(int[][] maze) {
        result = Integer.MAX_VALUE;
        n = maze.length;
        m = maze[0].length;

        Pos red = null, blue = null;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maze[i][j] == 1) red = new Pos(i, j, 1, false);
                if(maze[i][j] == 2) blue = new Pos(i, j, 2, false);
            }
        }

        dfs(red, blue, 0, maze, new boolean[n][m], new boolean[n][m]);
        return result == Integer.MAX_VALUE ? 0 : result;
    }
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{1,4}, {0,0},{2,3}})); // 3
        System.out.println(solution(new int[][] {{1, 0, 2}, {0, 0, 0}, {5, 0, 5}, {4, 0, 3}})); // 7
        System.out.println(solution(new int[][] {{1, 5}, {2, 5}, {4, 5}, {3, 5}})); // 0
        System.out.println(solution(new int[][] {{4, 1, 2, 3}})); // 0
        System.out.println(solution(new int[][] {{4, 3, 0, 0}, {5, 5, 5, 0}, {1, 0, 0, 0}, {2, 0, 0, 0}})); // 9
    }
}
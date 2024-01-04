package algorithm_java.BFS;

import java.util.*;

// programmers 250136 [PCCP 기출문제] 2번 / 석유 시추
public class pm250136 {
    static int n,m;
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    public static class Pos {
        int x; int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int bfs(int i, int j, int idx, int[][] land) {
        int cnt = 1;

        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(i, j));
        land[i][j] = idx;

        while(!q.isEmpty()) {
            Pos now = q.poll();

            for(int k = 0; k < 4; k++) {
                int nx = now.x + dx[k];
                int ny = now.y + dy[k];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m ||
                        land[nx][ny] != 1) continue;
                q.add(new Pos(nx, ny));
                land[nx][ny] = idx;
                cnt++;
            }
        }
        return cnt;
    }
    public static int solution(int[][] land) {
        int answer = 0;

        n = land.length;
        m = land[0].length;

        int idx = 2, cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[j][i] != 1) continue;
                cnt = bfs(j, i, idx++, land);
                list.add(cnt);
            }
        }

        if (idx == 2) return 0;

        boolean check[] = new boolean[idx - 2];
        int sum = 0, number = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                number = land[j][i];
                if (number > 1 && !check[number - 2]) {
                    check[number - 2] = true;
                    sum += list.get(number - 2);
                }
            }
            answer = answer < sum ? sum : answer;
            Arrays.fill(check, false);
            sum = 0;
            number = 0;
        }
        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}}));
        System.out.println(solution(new int[][] {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}}));
    }
}

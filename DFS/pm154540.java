package algorithm_java.DFS;

import java.util.*;

// programmers 154540 무인도 여행
class pm154540 {
    static int n,m;
    static char[][] map;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static class Pos {
        int x, y, food;
        Pos(int x, int y, int food) {
            this.x = x;
            this.y = y;
            this.food = food;
        }
    }

    public static int dfs(Pos now) {
        if(map[now.x][now.y] == '0') return 0;
        map[now.x][now.y] = '0';

        int food = now.food;
        for(int i = 0; i < 4; i++) {
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];
            if(0 <= nx && nx < n && 0 <= ny && ny < m && '1' <= map[nx][ny] && map[nx][ny] <= '9') {
                food += dfs(new Pos(nx, ny, map[nx][ny]-'0'));
            }
        }
        return food;
    }

    public static int[] uninhabitedIsland() {
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if('1' <= map[i][j] && map[i][j] <= '9') {
                    list.add(dfs(new Pos(i, j, (map[i][j]-'0'))));
                }
            }
        }

        Collections.sort(list);
        if(list.isEmpty()) return new int[] {-1};
        return list.stream().mapToInt(i->i).toArray();
    }

    public static int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];
        for(int i = 0; i < n; i++) {
            map[i] = maps[i].toCharArray();
        }
        return uninhabitedIsland();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"X591X", "X1X5X", "X231X", "1XXX1"})));
        System.out.println(Arrays.toString(solution(new String[]{"XXX","XXX","XXX"})));
    }
}
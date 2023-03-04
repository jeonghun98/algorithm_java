package algorithm_java.BOJ_SWEA_0303;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class swea5656_2 {

    static class point{
        int r,c,cnt;
        public point(int r, int c, int cnt) {
            super();
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    static int n, w, h, min;
    static int map[][];
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static Stack<Integer> stack = new Stack<>();

    public static void down(int[][] map) { // 벽돌 내리기
        //맨 아래행부터 위쪽 들여다보며 빈칸 만날때마다 내려놓을 벽돌 찾기
        // 각 열에 대해 윗행부터 아래행까지 벽돌만 스택에 넣어두고 빼서 아래행부터 채우기
        for(int c = 0; c < w; c++) {
            for(int r = 0; r < h; r++) {
                if(map[r][c] > 0) {
                    stack.push(map[r][c]);
                    map[r][c] = 0;
                }
            }
            int r = h - 1;
            while(!stack.isEmpty()) {
                map[r--][c] = stack.pop();
            }
        }
    }

    public static void boom(int[][] map, int r, int c, int cnt) {//폭발
        map[r][c] = 0;
        if(cnt == 1) return;

        //현벽돌의 cnt-1만큼 4방 체크
        for(int d = 0; d < 4; d++) {
            int nr = r;
            int nc = c;
            for(int k = 1; k <= cnt-1;k++) {
                nr += dr[d];
                nc += dc[d];
                if(nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc] > 0) {
                    boom(map,nr,nc,map[nr][nc]);
                }
            }
        }

    }

    private static int getRemain(int[][] map) { // 남은 벽돌 찾기
        int count = 0;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] > 0) count++;
            }
        }
        return count;
    }

    private static void copy(int[][] map, int[][] newMap) { // 배열 copy
        for(int i = 0; i < h; i++) {
            newMap[i] = Arrays.copyOf(map[i], map[i].length);
        }
    }

    public static boolean go(int cnt, int[][] map) { // 구슬 던지기

        int result = getRemain(map);

        if(result == 0) { // 벽돌 다 부시는 경우
            min = 0;
            return true;
        }

        if(cnt == n) { // 구슬 n개 다 던진 경우
            min = Math.min(result, min);
            return false;
        }

        int[][] newMap = new int[h][w];
        // 모든 열에 구슬 던져보기
        for(int c = 0; c < w; c++) { // c 구슬 던지는 열

            // 구슬에 처음 맞는 벽돌 찾기(위쪽에서)
            int r = 0;
            while(r < h && map[r][c] == 0) ++r;
            if(r == h) continue; // 맞은 벽돌 x -> 다음열 에 던져보기

            copy(map, newMap);

            //벽돌 부수기
            boom(newMap, r,c, newMap[r][c]);

            //벽돌 내리기
            down(newMap);
            //다음 구슬 던지러 가기
            if(go(cnt+1, newMap)) return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  null;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new int[h][w];


            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            go(0,map);
            System.out.println("#" + tc + " " + min);

        }
    }
}
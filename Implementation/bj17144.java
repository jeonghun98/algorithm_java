package algorithm_java.Implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;
// Baekjoon Online Judge 17144 미세먼지 안녕!
public class bj17144 {
    static int r,c,t;
    static int data[][];

    static Stack<finedust> dust = new Stack<>(); // 공기청정기 돌릴 동안 미세 먼지 넣을 stack

    static int dx[][] = {{0,-1,0,1},{0,1,0,-1}};
    static int dy[][] = {{1,0,-1,0},{1,0,-1,0}};

    static boolean access[] = new boolean[4];	// 접근 가능한 구역

    static pos[] air = new pos[2]; //공기 청정기
    static Deque<Integer> q = new ArrayDeque<>(); // 전체 미세먼지 넣을 q

    public static class finedust{ // 미세먼지
        int x, y, d;
        public finedust(int x, int y, int d) {
            super();
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static class pos{	//위치
        int x, y;
        public pos(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }

    public static int access(finedust f) { // 미세먼지 인접한 접근 확인
        int count = 0;
        Arrays.fill(access, false);

        for(int i = 0; i < 4; i++) {
            int nx = f.x + dx[0][i];
            int ny = f.y + dy[0][i];

            if(nx < 0 || nx >= r || ny < 0 || ny >= c || data[nx][ny] == -1) continue;

            access[i] = true;
            count++;
        }
        return count;
    }

    public static void spread_finedust() { // 미세먼지 확산
        while(!dust.isEmpty()) {
            finedust f = dust.pop();
            int num = access(f);

            if(num == 0) { // 확산 못 한다면 그대로 넣어줌
                data[f.x][f.y] += f.d;
                continue;
            }

            for(int i = 0; i < 4; i++) { // 인접한 네 방향에서 접근 가능하면 n/5
                if(access[i]) {
                    data[f.x+dx[0][i]][f.y+dy[0][i]] += (int) f.d / 5;
                }
            }
            data[f.x][f.y] += f.d - ((int) f.d / 5) * num;
        }
    }


    public static void air_purifier() { // q 사용 => 공기청청기로 미세먼지 밀기

        for(int a = 0; a < 2; a++) {
            int x = air[a].x;
            int y = air[a].y;
            int dir = 0;

            int rotation = 0;

            if(a == 0) rotation = c*2 + air[a].x * 2 - 3;
            else rotation = c*2 + (r - air[a].x - 1) * 2 - 3;

            for(int i = 0; i < rotation; i++) {
                int nx = x + dx[a][dir];
                int ny = y + dy[a][dir];
                if(nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    dir = (dir + 1) % 4; i--; continue;
                }
                q.offer(data[nx][ny]);
                x = nx; y = ny;
            }

            q.offerFirst(0);
            q.pollLast();

            x = air[a].x;
            y = air[a].y;
            dir = 0;


            for(int i = 0; i < rotation; i++) {
                int nx = x + dx[a][dir];
                int ny = y + dy[a][dir];

                if(nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    dir = (dir + 1) % 4; i--; continue;
                }

                data[nx][ny] = q.poll();
                x = nx; y = ny;
            }
        }
    }

    public static int find_finedust() { //미세먼지 찾기 => stack에 넣기, 총 미세먼지 return
        int dust_count = 0;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(data[i][j] != -1 && data[i][j] != 0) {
                    dust.add(new finedust(i,j,data[i][j]));
                    dust_count += data[i][j];
                    data[i][j] = 0;
                }
            }
        }
        return dust_count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());


        int cnt = 0;
        data = new int[r][c];
        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++) {
                int tmp = Integer.parseInt(st.nextToken());

                if(tmp == -1) {
                    air[cnt++] = new pos(i,j);
                    data[i][j] = -1;
                }
                else if(tmp != 0) dust.add(new finedust(i,j,tmp));
            }
        }

        int result = 0;
        for(int i = 0; i < t; i++) {
            //미세먼지 확산
            spread_finedust();
            //공기청정기
            air_purifier();
            //미세먼지 찾기
            result=find_finedust();
        }
        System.out.println(result);
    }

}

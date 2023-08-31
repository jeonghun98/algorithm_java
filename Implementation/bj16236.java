package algorithm_java.Implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
// Baekjoon Online Judge 16236 아기 상어
public class bj16236 {
    static int n;
    static int data[][];
    static boolean visited[][];

    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};


    static int baby_size; // 현재 상어 사이즈
    static Pos baby; // 아기 상어 객체

    static Stack<Pos> stack; // 상어가 먹을 수 있는 물고기


    public static class Pos{ // 위치, 거리
        int x, y, dis;
        public Pos(int x, int y) {
            this(x,y,0);
        }
        public Pos(int x, int y, int dis) {
            super();
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    public static int babySharkEat() { // 아기 상어 밥 시간

        int time = 0, fish_eat = 0; // 상어가 움직이는 시간, 상어가 먹은 물고기 수
        data[baby.x][baby.y] = 0; // 상어 위치 0

        while(true) {

            if(fish_eat == baby_size) { // 물고기 사이즈 만큼 먹으면 아기 상어 크기 증가
                baby_size++;
                fish_eat = 0;
            }

            Pos fish = bfs(baby); // 가까운 물고기 찾기
            if(fish.dis == Integer.MAX_VALUE) // 못찾으면 break
                break;

            // fish 먹기
            fish_eat++;
            data[fish.x][fish.y] = 0;

            // 상어 위치 바꾸기
            baby = new Pos(fish.x, fish.y);

            // 시간 증가
            time += fish.dis;
        }
        return time;
    }

    public static Pos bfs(Pos baby) { // 먹을 수 있는 물고기 위치 찾기
        for(int i = 0; i < n; i++)
            Arrays.fill(visited[i], false);

        Queue<Pos> q = new ArrayDeque<>();
        q.add(baby);

        visited[baby.x][baby.y] = true;

        while(!q.isEmpty()) {  // 먹을 수 있는 물고기 찾기
            Pos now = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny])
                    continue;

                if(data[nx][ny] <= baby_size) { // 지나갈 수 있다면
                    q.add(new Pos(nx,ny,now.dis+1));	// 경로 넣고
                    visited[nx][ny] = true;		// 방문 처리

                    if(0 < data[nx][ny] && data[nx][ny] < baby_size) {// 먹을 수 있는 물고기 stack에 넣기
                        stack.push(new Pos(nx,ny,now.dis+1));
                        int tmp = now.dis+1;
                    }
                }
            }
        }

        int min_dis = Integer.MAX_VALUE;
        int x = 0, y = 0;

        while(!stack.isEmpty()) { // 먹을 수 있는 물고기 중에서 가까운 물고기 찾기
            Pos now = stack.pop();
            if(min_dis > now.dis) { // 가까운 물고기 찾기
                x = now.x; y = now.y;
                min_dis = now.dis;
            }
            else if(min_dis == now.dis) { // 거리가 같다면
                if(now.x < x) {	// 가장 위에 있는 물고기
                    x = now.x; y = now.y;
                }
                else if(now.x == x && now.y < y) { // x의 위치까지 같다면 왼쪽 물고기 고르기
                    x = now.x; y = now.y;
                }
            }
        }
        return new Pos(x,y,min_dis);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());

        baby_size = 2; // 아기 상어 사이즈
        boolean oneSize = false; // 먹을 수 있는 물고기 있는지 확인

        visited = new boolean[n][n];
        data = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                data[i][j] =Integer.parseInt(st.nextToken());
                if(data[i][j] == 9) baby = new Pos(i,j);
                if(data[i][j] == 1) oneSize = true;
            }
        }

        if(!oneSize) { // 1 크기의 물고기가 없을 때
            System.out.println(0);
            return;
        }

        stack = new Stack<>(); // 먹을 수 있는 물고기 stack
        System.out.println(babySharkEat());
    }

}

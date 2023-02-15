package algorithm_java.BOJ0215;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj7576 {
    static int n,m;
    static int tomato[][];
    static Queue<int[]> q;

    public static boolean check_tomato() { // 0이라는 익지 않은 토마토 있는지 확인한는 메소드
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(tomato[i][j] == 0) return false;
            }
        }
        return true;
    }

    public static int bfs() { //bfs
        int result = -1;
        int dx[] = {0,1,0,-1};
        int dy[] = {1,0,-1,0};

        while(!q.isEmpty()) {
            int size = q.size();	//q사이즈별로 count하기 위해 while문 2개 사용
            while(--size >= 0){
                int xy[] = q.poll();
                for(int i = 0; i < 4; i++) {
                    int nx = xy[0] + dx[i];
                    int ny = xy[1] + dy[i];
                    if(nx < 0 || nx >= n || ny < 0 || ny >= m || tomato[nx][ny] != 0)
                        continue;
                    int temp[] = {nx,ny};
                    q.offer(temp);
                    tomato[nx][ny] = 1;
                }
            }result++;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        q = new ArrayDeque<int[]>();

        tomato = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());

                if(tomato[i][j] == 1) { //익은 토마토는 q에 넣는다.
                    int temp[] = {i,j};
                    q.offer(temp);
                }
            }
        }

        int result = bfs(); //익지 않은 토마토가 없다면 result , 아니라면 -1
        System.out.println(check_tomato()== true ? result : -1);
    }

}

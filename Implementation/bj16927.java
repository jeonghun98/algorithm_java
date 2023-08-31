package algorithm_java.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// Baekjoon Online Judge 16927 배열 돌리기 2
public class bj16927 {
    static Queue<Integer> q = new ArrayDeque<Integer>();
    static int x, y, dir;
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static boolean check[][];

    static int data[][];
    static int N,M,K;

    public static void array_change(int n, int m, int count) { // 회전 재귀 함수
        x = count; y = count; dir = 0;
        q.offer(data[x][y]);
        for(int i = 1; i < (n+m-2)*2; i++) {	//테두리 q에 넣기
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M || check[nx][ny]) {
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            q.offer(data[nx][ny]);
            x = nx; y = ny;
        }
        for(int r = 0; r < K % ((n+m-2)*2); r++) {	// r만큼 poll해서 offer
            q.offer(q.poll());
        }

        x = count; y = count; dir = 0;
        data[x][y] = q.poll();
        for(int i = 1; i < (n+m-2)*2; i++) {	//q의 값 다시 data에 넣기
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M || check[nx][ny]) {
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            data[nx][ny] = q.poll();
            check[nx][ny] = true;
            x = nx; y = ny;
        }
        n -= 2; m -= 2; count++;
        if(n <= 0 || m <= 0 || (n + m-2)*2 <= 1) return;
        array_change(n, m , count); // 안의 테두리로 재귀함수
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        data = new int[N][M];
        check = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        array_change(N, M, 0);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(data[i][j]+ " ");
            }
            sb.append("\n");
        }System.out.println(sb.toString());
    }
}


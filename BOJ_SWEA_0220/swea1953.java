package algorithm_java.BOJ_SWEA_0220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea1953 {
    static int n,m,r,c,l;
    static int data[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static int tunnel[][] = {{0,1,2,3},{1,3},{0,2},{0,3},{0,1},{1,2},{2,3}}; // 터널 구조물 타입을 순서대로 넣은 배열
    static int count;

    public static boolean check_next(int x, int y, int r) { //옆의 터널로 갈 수 있는지 확인 메소드
        if(r % 2 == 0) r = 2 - r;
        else r = r == 1 ? 3 : 1;

        int now_tunnel = data[x][y] - 1;
        if(now_tunnel == -1) return false;

        int connection[] = tunnel[now_tunnel];
        for(int i : connection) {
            if(i == r) return true; //옆의 터널과 연결된다면 true
        }return false;
    }

    public static void find_exit() {
        Queue<int[]> q = new ArrayDeque<int[]>();
        int tmp[] = {r,c};
        q.offer(tmp);

        int size = 0;
        count = 0;
        while(!q.isEmpty()) {
            size = q.size();
            while(--size >= 0) {
                tmp = q.poll();
                int x = tmp[0], y = tmp[1];

                if(data[x][y] == 9) continue; //방문했던 곳은 => 9로 체크했으므로 pass

                int now_tunnel = data[x][y] - 1;	//현재 위치의 터널 구조물 타입 확인
                if(now_tunnel == -1) continue;			//구조물 타입이 0이라면 pass

                int connection[] = tunnel[now_tunnel];	// 현재 구조물에서 갈수 있는 구조물 확인
                for(int i : connection) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(nx < 0 || nx >= n || ny < 0 || ny >= m || data[nx][ny] == 9 || data[nx][ny] == 0)
                        continue;

                    if(check_next(nx,ny,i)) {		// 현재 구조물에서 연결된 구조물을 확인하고 q에 넣기
                        int tmp0[] = {nx,ny};
                        q.add(tmp0);
                    }
                } data[x][y] = 9; count++;	//현재 위치 방문했음을 표시, 갈 수 있는 곳 + 1
            }
            if(--l == 0) break; // 시간이 끝나면 break
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for(int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            data = new int[n][m];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m;j++) {
                    data[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            find_exit();
            sb.append("#" + t + " " + count + "\n");
        }System.out.println(sb.toString());
    }

}

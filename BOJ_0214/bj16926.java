package algorithm_java.BOJ_0214;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj16926 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int data[][] = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int r = 0; r < k; r++) {	//r만큼 돌기s
            boolean check[][] = new boolean[n][m];
            int t_n = n, t_m = m;
            int x = 0, y = 0, dir = 0;
            int dx[] = {1,0,-1,0};	//아래 오른쪽 위 왼쪽 순서
            int dy[] = {0,1,0,-1};
            int b_temp = data[x][y];

            while(true) {	// 밖의 테두리 부터 돌기
                for(int i = 0; i < (t_n+t_m-2)*2; i++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if(nx < 0 || nx >= n || ny < 0 || ny >= m || check[nx][ny]) {
                        dir = (dir + 1) % 4;	//방향 틀기
                        nx = x + dx[dir];
                        ny = y + dy[dir];
                    }

                    int temp = data[nx][ny];
                    data[nx][ny] = b_temp;
                    check[nx][ny] = true;
                    x = nx; y = ny; b_temp = temp;
                }
                t_n -= 2; t_m -= 2; x++; y++; b_temp = data[x][y];	//안의 테두리로 이동
                if(t_n <= 0 || t_m <= 0 || (t_n+t_m-2)*2 <= 1)		//안의 테두리가 없다면 break
                    break;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(data[i][j]+ " ");
            }
            sb.append("\n");
        }System.out.println(sb.toString());
    }

}



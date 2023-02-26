package algorithm_java.BOJ_SWEA_0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2567 {
    static int data[][];
    static int n;
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};

    public static int Confetti() {
        int result = 0;

        for(int i = 1; i < 101; i++) { // 완탐
            for(int j = 1; j < 101; j++) {
                if(data[i][j] == 1) {
                    for(int k = 0; k < 4; k++) {  // 사방탐색
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(data[nx][ny] == 0) result++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        data = new int[102][102];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            for(int a = x; a < x + 10; a++) {
                for(int b = y; b < y + 10; b++) {
                    data[a][b] = 1;
                }
            }

        }
        System.out.println(Confetti());
    }
}

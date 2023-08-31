package algorithm_java.Floyd_Warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea1263 {
    static int n;
    static int data[][];
    static final int INF = Integer.MAX_VALUE / 2 - 1;

    public static int floyd() {
        for(int k = 0; k < n; k++) { // i->k->j
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    data[i][j] = Math.min(data[i][j], data[i][k] + data[k][j]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int sum = 0; // 한 사람에서 다른 모든 사람과의 거리 합
            for(int j = 0; j < n; j++) {
                sum += data[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            data = new int[n][n];

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if(tmp == 0 && i != j)
                        data[i][j] = INF;
                    else
                        data[i][j] = tmp;
                }
            }

            System.out.println("#" + tc + " " + floyd());

        }
    }

}


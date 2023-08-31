package algorithm_java.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 14890 경사로
public class bj14890 {
    static int n, l;
    static int data[][];
    public static int runway(){
        int row_result = 0;
        int col_result = 0;
        boolean row_visitied[][] = new boolean[n][n];
        boolean col_visitied[][] = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            boolean row_check = true;
            int row_before = data[i][0];
            int row_flat_cnt = 1;

            boolean col_check = true;
            int col_before = data[0][i];
            int col_flat_cnt = 1;

            for(int j = 1; j < n; j++) {
                if(row_check && !row_visitied[i][j]) {
                    int row_diff = row_before-data[i][j];
                    if( Math.abs(row_diff) > 1) {
                        row_check = false;
                    } else if(row_diff < 0) { //오르막길
                        if(row_flat_cnt < l)
                            row_check = false;
                    } else if(row_diff > 0) { //내리막길
                        int down = l-1;
                        int ny = j;
                        while(down-- > 0) {
                            ny++;
                            if(ny >= n || data[i][ny] != data[i][j]) {
                                row_check = false;
                                break;
                            }
                            row_visitied[i][ny] = true;
                        }
                    }

                    if(row_diff < 0) row_flat_cnt = 1;
                    else if(row_diff > 0) row_flat_cnt = 0;
                    else row_flat_cnt++;

                    row_before = data[i][j];
                }
                if(col_check && !col_visitied[j][i]) {
                    int col_diff = col_before-data[j][i];
                    if( Math.abs(col_diff) > 1) {
                        col_check = false;
                    } else if(col_diff < 0) { //오르막길
                        if(col_flat_cnt < l)
                            col_check = false;
                    } else if(col_diff > 0) { //내리막길
                        int down = l-1;
                        int nx = j;
                        while(down-- > 0) {
                            nx++;
                            if(nx >= n || data[nx][i] != data[j][i]) {
                                col_check = false;
                                break;
                            }
                            col_visitied[nx][i] = true;
                        }
                    }

                    if(col_diff < 0) col_flat_cnt = 1;
                    else if(col_diff > 0) col_flat_cnt = 0;
                    else col_flat_cnt++;

                    col_before = data[j][i];
                }

            }
            if(row_check) {
                row_result++;
            } if(col_check) {
                col_result++;
            }
        }
        return row_result+col_result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        data = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(runway());

    }
}

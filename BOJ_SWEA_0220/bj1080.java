package algorithm_java.BOJ_SWEA_0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1080 {
    static int n, m;
    static char data_a[][];
    static char data_b[][];

    public static void change_3(int x, int y) {	//x,y부터 3x3 행렬 뒤집기
        for(int i = x; i < x+3; i++) {
            for(int j = y; j < y+3; j++) {
                data_a[i][j] = data_a[i][j] == '1' ? '0' : '1';
            }
        }
    }

    public static boolean check() { // A 행렬과 B행렬 비교 메소드
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(data_a[i][j] != data_b[i][j]) {
                    return false;
                }
            }
        }return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data_a = new char[n][m];
        data_b = new char[n][m];

        for(int i = 0; i < n; i++)
            data_a[i] = br.readLine().toCharArray();
        for(int i = 0; i < n; i++)
            data_b[i] = br.readLine().toCharArray();


        if(check()) { //A = B => 0
            System.out.println(0);
            return;
        }


        if(n-3 < 0 || m-3 < 0) { // 3x3보다 작다면 => -1
            System.out.println(-1);
            return;
        }

        int count = 0;	 // => 순서대로 값이 다르다면 3x3행렬 바꾸기
        for(int i = 0; i < n-2; i++) {
            for(int j = 0; j < m-2; j++) {
                if(data_a[i][j] != data_b[i][j]) {
                    change_3(i,j); count++;
                }
            }
        }

        if(check())	// A = B => 연산 횟수
            System.out.println(count);
        else		// 다르다면 => -1
            System.out.println(-1);
    }

}

package algorithm_java.BOJ_SWEA_0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1992 {
    static int n;
    static char data[][];
    static StringBuilder sb = new StringBuilder();

    public static boolean check(int row, int col, int size) {	// 압축이 가능한지 확인하는 메소드
        char flag = data[row][col];

        for(int i = row; i < row + size; i++) {
            for(int j = col; j < col + size; j++) {
                if(flag != data[i][j]) {	// 0,1이 섞여있다면 압축 x -> false
                    return false;
                }
            }
        }return true;

    }
    public static void solve(int row, int col, int size) {	// 4분할해서 압축하는 재귀 함수
        int half = size / 2;

        if (size == 1) {
            if(data[row][col] == 0) sb.append("0");
            else sb.append("1");
            return;
        }

        //좌측상단
        if (!check(row,col,half)) {  // 압축되지 않는다면 다시 분할
            sb.append("(");
            solve(row, col, half);
            sb.append(")");
        }
        else {
            sb.append(data[row][col]);	// 압축된다면 해당 값 입력
        }

        //우측상단
        if (!check(row,col+half,half)) {
            sb.append("(");
            solve(row, col+half, half);
            sb.append(")");
        }
        else {
            sb.append(data[row][col+half]);
        }

        //좌측하단
        if (!check(row+half,col,half)) {
            sb.append("(");
            solve(row+half, col, half);
            sb.append(")");
        }
        else {
            sb.append(data[row+half][col]);
        }

        //우측하단
        if (!check(row+half,col+half,half)) {
            sb.append("(");
            solve(row+half, col+half, half);
            sb.append(")");
        }
        else {
            sb.append(data[row+half][col+half]);
        }
    }

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        data = new char[n][n];
        for(int i = 0; i < n; i++) {
            data[i] = br.readLine().toCharArray();
        }

        if(check(0,0,n)) {	// 하나로 압축이 가능하다면
            System.out.println(data[0][0]);	// 해당 결과 출력
            return;
        }

        // 하나로 압축이 안된다면

        sb.append("(");	// 괄호 열고
        solve(0,0,n);	// 4분할 한 결과 넣고
        sb.append(")");	// 괄호 닫기

        System.out.println(sb.toString()); // 출력

    }
}

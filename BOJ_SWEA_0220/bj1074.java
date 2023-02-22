package algorithm_java.BOJ_SWEA_2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1074 {
    static int n,r,c;
    static int result;

    //	public static void z(int n, int num, int x, int y) {
//		if(n==1) {
//			for(int i = x; i < x+2; i++) {
//				for(int j = y; j < y+2; j++) {
//					if(i == r && j == c) {
//						reㄴsult = num;
//						return;
//					}
//					num++;
//				}
//			}
//		}
//		for(int i = 0, k = -1; i < 2; i++) {
//			for(int j = 0; j < 2; j++) {
//				int tmp = (int)Math.pow(2, (n-1)); k++;
//
//				if(x+i * tmp > r || x+ i * tmp + tmp < r+1) continue;
//				if(y+j * tmp > c || y+j * tmp + tmp < c+1) continue;
//
//				z(n-1, num + tmp * tmp * k, x+ i * tmp, y+j * tmp);
//			}
//		}
//	}
    private static void solve(int row, int col, int size) {
        int half = size / 2;
        if (size == 1) {
            System.out.println(result);
            return;
        }
        if (r < row + half && c < col + half) { //좌측상단
            solve(row, col, half);
        }

        else if (r < row + half && c >= col + half) { //우측상단
            result += size * size / 4;
            solve(row, col + half, half);
        }

        else if (r >= row + half && c < col + half) {	//좌측하단
            result += size * size / 4 * 2;
            solve(row + half, col, half);
        }

        else {										//우측하단
            result += size * size / 4 * 3;
            solve(row + half, col + half, half);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        solve(0,0,(int) Math.pow(2,n));
    }
}


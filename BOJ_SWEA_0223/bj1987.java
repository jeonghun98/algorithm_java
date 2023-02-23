package algorithm_java.BOJ_SWEA_0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1987 {
    static int r,c;
    static char alphabet[][];
    static boolean check[];

    static int dx[] = {-1,0,1,0}; //북동남서
    static int dy[] = {0,1,0,-1};
    static int result;

    static void move(int x, int y, int cnt) {
        for(int i = 0; i < 4; i++) { // 사방탐색
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= r || ny < 0 || ny >= c)
                continue;

            if(check[alphabet[nx][ny]-'A']) {   // 움직일 곳이 방문한 알파벳이라면 max로 비교해서 result 갱신
                result = Math.max(result, cnt);
                continue;
            }

            check[alphabet[nx][ny]-'A'] = true; // 방문하지 않은 알파벳이면 방문체크 후 재귀함수
            move(nx, ny, cnt+1);
            check[alphabet[nx][ny]-'A'] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        alphabet = new char[r][c];
        check = new boolean[26];
        for(int i = 0; i < r; i++) {
            alphabet[i] = br.readLine().toCharArray();
        }
        // => 입력

        result = Integer.MIN_VALUE;
        check[alphabet[0][0]-'A'] = true; // 말의 알파벳 체크
        move(0,0,1);    // 재귀함수 호출, 움직임은 현재 말의 칸도 포함하기 때문에 +1
        System.out.println(result);
    }
}

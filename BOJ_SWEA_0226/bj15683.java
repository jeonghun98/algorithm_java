package algorithm_java.BOJ_SWEA_0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj15683 {

    static int n,m;
    static int data[][], save[][]; //지도 데이터, 원본 저장 데이터

    static int dx[] = {0,1,0,-1}; // 동남서북
    static int dy[] = {1,0,-1,0};

    static int cctv_array[][] = {{0},{0,2},{0,3},{0,2,3},{0,1,2,3}}; // cctv 방향 => 1 ~ 5번

    static CCTV cctv[]; //cctv 객체를 넣을 배열
    static int cctv_rotation[]; // cctv 회전 배열
    static int cctv_count;  // cctv 개수

    static int result; // 사각 지대 최소 크키 저장(출력값)

    public static class CCTV{
        int x, y, num;

        public CCTV(int x, int y, int num) { // cctv 위치와 방향
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static void Blind_Spot() { // 사각 지대 구하기
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(data[i][j] == 0) cnt++;
            }
        }
        result = Math.min(result, cnt); //사각 지대 최소 크기 구하기
    }

    public static void reset() { // 지도 초기화
        for(int i = 0; i < n; i++) {
            data[i] = Arrays.copyOf(save[i], save[i].length);
        }
    }

    public static void cctv_search(){ // cctv 감시
        for(int i = 0; i < cctv_count; i++) {
            int cctv_number = cctv[i].num; // cctv의 종류
            int rotation = cctv_rotation[i]; //cctv 회전 횟수

            for(int num : cctv_array[cctv_number]) { //cctv 종류의 방향들
                int k = (num + rotation) % 4; //cctv 회전
                int nx = cctv[i].x; // 현재 cctv 위치
                int ny = cctv[i].y;

                while(true) {
                    nx += dx[k];    // cctv 방향으로 움직이기
                    ny += dy[k];
                    if(nx < 0 || nx >= n || ny < 0 || ny >= m || data[nx][ny] == 6) // 벽이나 배열 넘어가면 break
                        break;
                    if(data[nx][ny] == 0) data[nx][ny] = 9; // 빈칸이라면 cctv check ('#'대신 9)
                }
            }
        }
        Blind_Spot(); // 사각지대 찾기
        reset();    // 지도 초기화
    }

    public static void monitoring(int cnt) { // 90도 회전 조합

        if(cnt == cctv_count) {
            cctv_search(); //cctv 감시
            return;
        }
        for(int i = 0; i < 4; i++) {
            cctv_rotation[cnt] = i; // cctv 회전저장
            monitoring(cnt+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new int[n][m];
        save = new int[n][m];
        cctv = new CCTV[8];
        cctv_count = 0;
        result = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                data[i][j] = save[i][j] = tmp; // 지도, 원본 데이터 저장

                if(tmp != 0 && tmp != 6) {
                    cctv[cctv_count++] = new CCTV(i,j,tmp-1); //cctv는 위치와 방향 저장
                }

            }
        }
        cctv_rotation = new int[cctv_count]; // cctv 회전 배열 -> cctv 개수 만큼 생성
        monitoring(0); // 감시
        System.out.println(result); // 출력
    }
}

package algorithm_java.BOJ_SWEA_0216;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj17406 {
    static int n,m,k;
    static int r_array[][];
    static int result;
    static boolean isSelected[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int data[][] = new int [n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <m; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = Integer.MAX_VALUE;
        r_array = new int[k][3];	//회전 연산 -> 배열
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            r_array[i][0] = Integer.parseInt(st.nextToken());
            r_array[i][1] = Integer.parseInt(st.nextToken());
            r_array[i][2] = Integer.parseInt(st.nextToken());
        }

        isSelected = new boolean[k];
        permu(0, data);	// 회전 연산을 순열
        System.out.println(result);

    }
    public static void rotation(int index, int data[][]) {
        int dx[] = {0,1,0,-1};
        int dy[] = {1,0,-1,0};

        int r = r_array[index][0];
        int c = r_array[index][1];
        int s = r_array[index][2];

        int start_x = r-s-1, start_y = c-s-1, end_x = r+s-1, end_y = c+s-1;	//회전연산의 시작과 끝 index
        int square = 2*s+1;	// square / 2 -> 테두리 돌리는 횟수
        int now_square = square;

        for(int line = 0; line < (square)/2; line++) {	// 테투리 밖 -> 안
            int x = start_x; int y = start_y;
            int tmp = data[x][y];
            for(int i = 0, dir = 0; i < now_square*4-4;i++) {	// 테두리 돌기
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx < start_x || nx > end_x || ny < start_y || ny > end_y) {	// 벽 만나면 회전
                    dir = (dir+1) % 4;
                    nx = x + dx[dir];
                    ny = y + dy[dir];
                }
                int next_tmp = data[nx][ny];
                data[nx][ny] = tmp;
                tmp = next_tmp;
                x = nx; y = ny;
            }
            now_square -= 2; start_x += 1; start_y += 1; end_x -= 1; end_y-= 1;
        }
    }
    public static void min_num(int data[][]) {	// 현재 배열의 최솟값 찾기
        for(int i = 0; i < n; i++) {
            int tmp = 0;
            for(int j = 0; j <m; j++) {
                tmp += data[i][j];
            }
            result = Math.min(result, tmp);
        }
    }


    public static void permu(int cnt, int data[][]) {	//순열로 회전 연산의 모든 경우 실행하기 -> 최솟값 찾기
        if(cnt == k) {				//모든 연산이 끝나면
            min_num(data);			//해당 배열의 최솟값 찾기
            return;
        }
        for(int i = 0; i < k; i++) {
            if(isSelected[i]) continue;

            int tmp[][] = new int[n][m];
            for (int a = 0; a < n; a++) {
                tmp[a] = Arrays.copyOf(data[a], data[a].length);
            }
            rotation(i, tmp);		// 회전 연산
            isSelected[i] = true;	// 사용 체크
            permu(cnt+1, tmp);		// 순열 재귀
            isSelected[i] = false;
        }
    }
}


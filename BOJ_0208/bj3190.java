package algorithm_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj3190 {

    static int n,k,l;
    static int data[][], time[];
    static char time_rotation[];

    public static void snack(){
        Queue<int []> queue = new LinkedList<>();

        int result_time = 0;   // 지난 시간(출력값)
        int x = 1, y = 1; // 현재 위치

        data[x][y] = 1; // 시작위치 설정
        int start_p[] = {x,y};queue.offer(start_p);

        int rotation = 0; // 0 ~ 3
        int change_count = 0; // 방향 변경 change_count <= l

        int dx[] = {0,1,0,-1}; // 시계방향으로
        int dy[] = {1,0,-1,0};

        //사과 -> 9, 뱀 -> 1, 빈공간 -> 0
        while(true) {
//            for(int i = 1; i <= n; i++) {
//                for(int j = 1; j <= n; j++) {
//                    System.out.print(data[i][j] + " ");
//                } System.out.println();
//            }
//            System.out.println("=========================");
            result_time++; //시간++
            int nx = x + dx[rotation];  //현재 방향으로 움직임
            int ny = y + dy[rotation];
            if(nx < 1 || nx > n || ny < 1 || ny > n || data[nx][ny] == 1) // 벽이거나 뱀 꼬리만나면 break
                break;

            if(data[nx][ny] == 0) { //사과가 아니라면 꼬리 제거
                if(!queue.isEmpty()) {
                    int temp[] = queue.poll();  //Q에 넣었던 꼬리 제거
                    data[temp[0]][temp[1]] = 0; // 1 -> 0으로 변환
                }
            } else {

            }
            data[nx][ny] = 1;   //현재 위치 뱀으로 변경
            int temp[] = {nx,ny};   //Q에 뱀 위치 넣기
            queue.offer(temp);

            x = nx; y = ny;

            //방향 전환 -> time의 방향 전환 시간이랑 현재 시간 확인 / change_count -> 현재 방향 몇번 바꿨는지 count
            if(change_count < l && time[change_count] == result_time) {
                if(time_rotation[change_count] == 'D')
                    rotation = (rotation + 1) % 4;  //오른쪽
                else
                    rotation = rotation - 1 < 0 ? 3 : rotation - 1; //왼쪽
                change_count++;
            }
        }
        System.out.println(result_time);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        data = new int[n+1][n+1];
        k = Integer.parseInt(br.readLine());

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            data[x][y] = 9;
        }

        l = Integer.parseInt(br.readLine());
        time = new int[l];
        time_rotation = new char[l];
        for(int i = 0; i < l; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            time_rotation[i] = st.nextToken().charAt(0);
        }
        snack();
    }
}

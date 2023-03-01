package algorithm_java.BOJ_SWEA_0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea1767 {
    static int n;

    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};

    static ArrayList<pos> core;

    static int Max_connect;
    static int result;
    static int wire;

    static int cell[][];

    public static class pos {
        int x,y;

        public pos(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }

    public static void copy(int data[][]) { //현재 배열 copy
        cell = new int[n][n];
        for(int i = 0; i < n; i++)
            cell[i] = Arrays.copyOf(data[i], data[i].length);
    }


    public static boolean powercell(int core_index, int dir) { // 해당 index의 코어 전선 연결 확인 / wire : 전선 길이
        wire = 0;
        boolean success = true;

        pos now = core.get(core_index);
        int nx = now.x;
        int ny = now.y;
        while(true) {
            nx += dx[dir];
            ny += dy[dir];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n)
                break;
            if(cell[nx][ny] == 1) {
                success = false;
                break;
            }
            wire++;
            cell[nx][ny] = 1;
        }

        wire = success? wire : -1; // 전선이 연결된다면 전선 길이 update, 아니라면 -1
        return success;	// 전선이 연결되면 true, 아니면 false
    }


    public static void direction(int cnt, int data[][], int total_wire, int connect_core) { // core의 개수만큼 전선 사방 탐색
        if(cnt == core.size()) { // 모든 core의 방향이 정해졌다면

            if(connect_core > Max_connect) { //현재 연결 core가 크다면 max core 연결수와 전선 길이 update
                Max_connect = connect_core;
                result = total_wire;
            }
            else if(connect_core == Max_connect) //현재 연결 core가 작다면 전선 길이만 update
                result = Math.min(result, total_wire);
            return;
        }
        for(int i = 0; i < 4; i++) {

            copy(data); //현재 cell copy 해서 사용

            // 남은 core의 수 + 연결한 코어 수가 max core보다 작다면  => return
            if(core.size() - cnt + connect_core < Max_connect)
                return;

            //전선이 연결 할 수 있는 방향이고 현재 연결 가능하다면 update하고 재귀함수
            if(powercell(cnt, i))
                direction(cnt+1, cell, total_wire+wire, connect_core+1); // 현재 core가 연결한 전선 길이 더해서 update, 연결된 core + 1

                // 전선을 연결할 수 없는 방향 이거나 현재 연결이 안된다면 update x하고 계속 진행
            else
                direction(cnt+1, cell, total_wire, connect_core);

        }
    }

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());

            int data[][] = new int[n][n];
            core = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    data[i][j] = Integer.parseInt(st.nextToken());

                    //가장 자리를 제외한 core 위치 저장
                    if(data[i][j] == 1 && !(i == 0 || j == 0 || i == n-1 || j == n-1))
                        core.add(new pos(i,j));
                }
            }

            Max_connect = 0; // 가장 많은 core연결 수
            result = Integer.MAX_VALUE;

            direction(0, data, 0,0);

            sb.append("#" + tc + " ");
            sb.append(result == Integer.MAX_VALUE ? 0  + "\n": result + "\n");
        }
        System.out.println(sb.toString());
    }
}
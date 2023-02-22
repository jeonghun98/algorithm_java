package algorithm_java.BOJ_SWEA_0222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea1873 {
    static int h,w; // 높이, 너비
    static char data[][];	//배틀 필드

    static position tank;	// 현재 전차 위치와 방향

    static int game_num;	// 게임 입력 길이
    static char game_input[];	// 게임 문자

    static int dx[] = {-1,0,1,0}; //북동남서
    static int dy[] = {0,1,0,-1};

    public static class position{ // 전차 위치와 방향
        int x,y,rotation;
        public position(int x,int y,int rotation) {
            this.x = x;
            this.y = y;
            this.rotation = rotation;
        }
    }

    public static void game() {
        for(int index = 0; index < game_num; index++) {	// 게임의 입력을 모두 실행
            char now_input = game_input[index];

            if(now_input == 'S') {	// 슈팅 입력
                int nx = tank.x + dx[tank.rotation];	// 현재 전차의 방향위치에서 + 1 확인
                int ny = tank.y + dy[tank.rotation];
                while(true) {
                    if(nx < 0 || nx >= h || ny < 0 || ny >= w || data[nx][ny] == '#') break;	// 강철이면 break

                    if(data[nx][ny] == '*') {	// 벽돌이면 벽돌 파괴하고 break
                        data[nx][ny] = '.';
                        break;
                    }

                    nx += dx[tank.rotation];	// 평지나 물이라면 포탄 계속 전진
                    ny += dy[tank.rotation];
                }
            }
            else {	//  방향 입력
                int tmp_rotation = 0; char tmp_char='^';	// 입력받은 방향을 저장할 변수

                if(now_input == 'U') {tmp_rotation = 0; tmp_char = '^';}
                else if(now_input == 'R') {tmp_rotation = 1; tmp_char = '>';}
                else if(now_input == 'D') {tmp_rotation = 2; tmp_char = 'v';}
                else if(now_input == 'L') {tmp_rotation = 3; tmp_char = '<';}

                int nx = tank.x + dx[tmp_rotation];	// 입력받은 방향으로 위치
                int ny = tank.y + dy[tmp_rotation];

                tank.rotation = tmp_rotation;	// 전차의 방향을 바꿔주고 필드의 전차 방향도 바꾸기
                data[tank.x][tank.y] = tmp_char;

                if(nx < 0 || nx >= h || ny < 0 || ny >= w || data[nx][ny] != '.') continue; //입력받은 방향의 위치가 갈 수 있는지 확인
                data[tank.x][tank.y] = '.';	// 해당 위치로 갈 수 있다면 => 현재 위치는 . / 움직일 전차 위치 필드에 char 넣기 / 전차의 방향도 바꾸기
                data[nx][ny] = tmp_char;
                tank.x = nx; tank.y = ny;
            }

//			for(int i = 0; i < h; i++) {
//				for(int j = 0; j < w; j++) {
//					System.out.print(data[i][j]);
//				}System.out.println();
//			}
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            data = new char[h][w];
            for(int i = 0; i < h; i++) {
                data[i] = br.readLine().toCharArray();
                for(int j = 0; j < w; j++) {
                    if(data[i][j] == '^') tank = new position(i,j,0); //현재 전차의 위치 저장
                    else if(data[i][j] == '>') tank = new position(i,j,1);
                    else if (data[i][j] == 'v') tank = new position(i,j,2);
                    else if (data[i][j] == '<') tank = new position(i,j,3);
                }
            }

            game_num = Integer.parseInt(br.readLine());
            game_input = br.readLine().toCharArray();

            game(); // 게임 실행

            sb.append("#" + tc + " "); //출력
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    sb.append(data[i][j]);
                }sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
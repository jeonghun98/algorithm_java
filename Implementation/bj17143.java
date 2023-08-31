package algorithm_java.Implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// Baekjoon Online Judge 17143 낚시왕
public class bj17143 {
    static int r,c,m;
    static shark[] sharks;
    static int ocean[][];

    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,1,-1};

    public static class shark{ // 상어
        int x,y,s,d,z;

        public shark(int x, int y, int s, int d, int z) {
            super();
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void sharks_rolocation(int index) { // 상어 움직이기

        int x = sharks[index].x;
        int y = sharks[index].y;
        int d = sharks[index].d;
        int s = sharks[index].s;

        if(d == 0 || d == 1) { // 위 아래
            int cycle = r * 2 - 2; // 사이클

            if(d == 0) x = (cycle - x) + s; // index가 작아지는 방향이므로 cycle - x 해서 위치 설정 후 +s 움직임
            else x = x + s; // x에서 s만큼 움직이기

            x %= cycle; // 사이클로 나누기

            if(x < r) { // 나머지가 r 미만
                sharks[index].x = x; // 위치 그대로
                sharks[index].d = 1; // down 방향
            }
            else {
                sharks[index].x = cycle - x; // 사이클 - s
                sharks[index].d = 0; // up
            }
        }

        else { // 오른쪽 왼쪽 => 위와 같은 방식
            int cycle = c * 2 - 2;

            if(d == 3) y = (cycle - y) + s;
            else y = y + s;

            y %= cycle;

            if(y < c) {
                sharks[index].y = y;
                sharks[index].d = 2; // 오른쪽
            }
            else {
                sharks[index].y = cycle - y;
                sharks[index].d = 3; // 왼쪽
            }
        }
    }

    public static void remove_shark(shark remove) { // 상어 삭제
        for(int i = 0; i < m; i++) {
            if(sharks[i] == null) continue; // 사라진 상어라면 pass

            // 잡은 상어 삭제 (위치와 크기로 확인)
            if(sharks[i].x == remove.x && sharks[i].y == remove.y && sharks[i].z == remove.z) {
                sharks[i] = null;
                return;
            }
        }
    }
    public static void relocation() { // 바다에 상어 재배치

        for(int i = 0; i < r; i++)
            Arrays.fill(ocean[i], 0);

        for(int i = 0; i < m; i++) {
            if(sharks[i] == null) continue; // 사라진 상어라면 pass

            sharks_rolocation(i);	// 상어 움직이기

            int x = sharks[i].x;
            int y = sharks[i].y;

            if(ocean[x][y] != 0) { // 이미 상어가 존재 한다면
                if(ocean[x][y] > sharks[i].z) { // 작은 상어가 들어왔다면
                    remove_shark(sharks[i]);	// 작은 상어 삭제
                    continue;
                }
                else {
                    remove_shark(new shark(x,y,0,0,ocean[x][y])); // 큰 상어가 들어왔다면 원래 있단 상어 삭제
                }
            }
            ocean[x][y] = sharks[i].z;	// 다시 상어 크기 할당

        }
    }

    public static void fishing() { // 상어 잡기
        int fish_size = 0;

        for(int i = 0; i < c; i++) { //열을 움직이면서 상어 잡기

            //가장 가까운 상어 잡기
            for(int j = 0; j < r; j++) {
                if(ocean[j][i] != 0) {
                    fish_size += ocean[j][i];
                    remove_shark(new shark(j,i,0,0,ocean[j][i])); // 잡은 상어 삭제
                    break;
                }
            }

            relocation(); // 상어 재배치
        }
        System.out.println(fish_size);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ocean = new int[r][c];

        sharks = new shark[m];  // 상어 넣은 배열
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            sharks[i] = new shark(x, y, s, d, z);

            ocean[x][y] = z; // 바다
        }

        fishing(); // 상어 잡기
    }

}

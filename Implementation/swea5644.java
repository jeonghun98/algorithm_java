package algorithm_java.Implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea5644 {
    static int m,a;

    static int dx[] = {0,-1,0,1,0}; // 이동방향 0,1,2,3,4 순서대로 작성
    static int dy[] = {0,0,1,0,-1};

    static pos user_a, user_b;	// 현재 유저의 위치
    static int data_a[], data_b[];	// 유저가 움직일 위치

    static AP ap_array[];	// AP의 정보를 넣을 배열

    public static class pos{	// 유저의 위치를 넣을 클래스
        int x, y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class AP{	// AP의 위치, 충전범위, 성능을 넣을 클래스
        int x, y, c, p;
        public AP(int x, int y, int c, int p) {
            this.x = x; this.y = y;
            this.c = c; this.p = p;
        }
    }

    public static int distance(pos p1, AP ap1) {	// 거리 계산
        return Math.abs(p1.x - ap1.x) + Math.abs(p1.y - ap1.y) <= ap1.c ? ap1.p : 0;
    }

    public static int charge() {
        int result = 0;

        for(int i = 0; i <= m; i++) {	// 유저가 m번 움직일 때마다 충전양 확인

            int charge1 = Integer.MIN_VALUE;	// a 유저 현재 위치의 충전량
            int charge2 = Integer.MIN_VALUE;	// b 유저 현재 위치의 충전량
            int charge_total = Integer.MIN_VALUE;

            for(int j = 0; j < a; j++) {
                charge1 = distance(user_a, ap_array[j]);
                for(int k = 0; k < a; k++) {
                    charge2 = distance(user_b, ap_array[k]);

                    // 현재 위치의 최대 충전량 구하기
                    if(charge1 == charge2 && j == k)	// 같은 AP의 접속하면 n/2 * 2
                        charge_total = Math.max(charge_total, charge1);
                    else
                        charge_total = Math.max(charge_total, charge1 + charge2); // 다른 AP 접속하면 a,b의 충전량 더함
                }
            }

            result += charge_total; // 현재 최대 충전량 최종값의 더하기
            if(i == m) break;	// m이면 break

            int x = user_a.x;
            int y = user_a.y;
            int nx = x + dx[data_a[i]];
            int ny = y + dy[data_a[i]];

            if(!(nx < 1 || nx > 10 || ny < 1 || ny > 10)) {	// 다음위치로 움직일 수 있다면 위치 갱신
                user_a.x = nx; user_a.y = ny;
            }

            x = user_b.x;
            y = user_b.y;
            nx = x + dx[data_b[i]];
            ny = y + dy[data_b[i]];

            if(!(nx < 1 || nx > 10 || ny < 1 || ny > 10)) { // 다음위치로 움직일 수 있다면 위치 갱신
                user_b.x = nx; user_b.y = ny;
            }

        }return result; // 총 충전한 양 return
    }

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {

            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());

            user_a = new pos(1,1); //a 유저의 위치
            user_b = new pos(10,10); //b 유저의 위치

            data_a = new int[m];	// a가 움직일 방향의 정보
            data_b = new int[m];	// b가 움직일 방향의 정보

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < m; i++)
                data_a[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < m; i++)
                data_b[i] = Integer.parseInt(st.nextToken());

            ap_array = new AP[a]; //모든 AP의 정보를 넣을 배열
            for(int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                ap_array[i] = new AP(y,x,c,p); // 각 AP의 정보
            }

            sb.append("#" + tc + " " + charge()+"\n");

        }System.out.println(sb.toString()); // 출력
    }
}

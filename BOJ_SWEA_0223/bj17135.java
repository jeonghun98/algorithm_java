package algorithm_java.BOJ_SWEA_0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class bj17135 {

    static int n,m,d;
    static int data[][];

    static List<Pos> enemy;
    static Pos[] user;

    static int result;

    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 한 칸씩 아래로 내려오기
    public static void down(int tmp[][]) {
        for(int i = n-1; i >= 0; i--) {
            for(int j = 0; j < m; j++) {
                if(i == 0) tmp[i][j] = 0;
                else tmp[i][j] = tmp[i-1][j];
            }
        }
    }

    // 공격범위 체크
    public static int attack(Pos u, Pos e) {
        int math = Math.abs(u.x - e.x) + Math.abs(u.y - e.y);
        return math <= d ? math : -1;
    }

    // 현재 존재하는 적 -> list enemy
    public static void find_enemy(int tmp[][]) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(tmp[i][j] == 1) enemy.add(new Pos(i,j));
            }
        }
    }

    // 조합 재귀 + 게임 진행
    public static void game(int start, int cnt) {
        if(cnt == 3) {	// 궁수 3명의 위치 결정됨
            int total_count = 0;

            int tmp_data[][] = new int[n+1][m]; //격자판을 담을 임시 배열
            for(int i = 0; i < n; i++) tmp_data[i] = Arrays.copyOf(data[i], data[i].length);

            //적을 n번만큼 밑으로 한칸씩 내림
            for(int i = 0; i < n; i++) {
                int count = 0;

                enemy = new LinkedList<Pos>();
                find_enemy(tmp_data); //현재 있는 적 파악
                if(enemy.size() == 0) break; // 적이 없다면 break

                // 궁수 마다 가까운 적을 찾는다
                for(Pos u : user) {

                    int min_dis = Integer.MAX_VALUE; // 가까운 적 거리랑 위치 저장
                    Pos min_pos = null;

                    for(int e = 0; e < enemy.size(); e++) { // 궁수 한명마다 모든 적 확인

                        int user_enemy_dis = attack(u, enemy.get(e)); // 궁수와 적과의 거리 확인
                        if(user_enemy_dis == -1 || min_dis < user_enemy_dis) continue;

                        if (min_dis > user_enemy_dis) {	// 가까운 적을 발견
                            min_dis = user_enemy_dis;
                            min_pos = new Pos(enemy.get(e).x, enemy.get(e).y);
                        }
                        else {		// 같은 거리의 적이 있다면 가장 왼쪽 저장
                            if(min_pos.y > enemy.get(e).y) {
                                min_pos = new Pos(enemy.get(e).x, enemy.get(e).y);
                            }
                        }
                    }
                    // 공격 범위 내에 존재하고 아직 공격하지 않았다면 => 적을 제외하고 제거 횟수 count
                    if(min_pos != null && tmp_data[min_pos.x][min_pos.y] != 0) {
                        tmp_data[min_pos.x][min_pos.y] = 0;
                        count++;
                    }
                }
                down(tmp_data); // 3명의 궁수 모두 공격 후 아래로 한칸 이동
                total_count += count;	// 이번 회차 총 제거 횟수
            }
            result = Math.max(result, total_count); // 모든 조합에서 Max 제거 횟수 체크
            return;
        }

        for(int i = start; i < m; i++) { //조합 재귀 함수 호출
            user[cnt] = new Pos(n,i);
            game(i+1, cnt+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        data = new int[n][m];
        user = new Pos[3];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MIN_VALUE;
        game(0,0);
        System.out.println(result);

    }
}

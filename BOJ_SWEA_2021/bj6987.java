package algorithm_java.BOJ_SWEA_2021;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj6987 {
    static int result;
    static int data[][];

    public static boolean check() { // 결과 예측이 되는지 확인
        for(int i = 0; i < 6 ; i++) {
            for(int j = 0; j < 3 ; j++) {
                if(data[i][j] > 0)
                    return false;
            }
        }

        return true;
    }

    public static void possible(int team_a, int team_b) {
        if(check()) {	// 모든 데이터가 0이라면 결과 예측 가능
            result = 1; return;
        }

        if(team_b == 6) { // 해당 팀의 경기를 다 확인했으면 다음 팀 확인
            team_a++;
            team_b = team_a + 1;
        }

        if(team_a >= 5) // 모든 팀을 다 확인했다면 return
            return;

        for(int i = 0; i < 3; i++) {  //모든 경기 확인
            if(data[team_a][i]> 0 && data[team_b][2-i] > 0) {
                data[team_a][i]--;
                data[team_b][2-i]--;
                possible(team_a, team_b+1);
                data[team_a][i]++;
                data[team_b][2-i]++;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        data = new int[6][3];
        for(int t = 0; t < 4; t++) {
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < 6 ; i++) {
                for(int j = 0; j < 3 ; j++) {
                    data[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = 0;
            possible(0,1);
            sb.append(result + " ");

        }System.out.println(sb.toString());

    }

}

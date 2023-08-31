package algorithm_java.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// Baekjoon Online Judge 1759 암호 만들기
public class bj1759 {

    static int L, C;
    static char data[];
    static int vowels,consonants;
    static char result[];
    static StringBuilder sb;

    static void pw(int start, int cnt) { // 암호 찾는 재귀 함수(조합)
        if(cnt == L) {	// L개의 문자열이라면
            if(vowels > 0 || consonants > 0) return; // 모음 한개, 자음 두개 조건을 못 채우면 continue

            for(char i : result) sb.append(i);	// 찾은 암호 StringBuilder 넣기
            sb.append("\n");

            return;
        }
        for(int i = start; i < C; i++) {
            if(data[i] == 'a' || data[i] == 'e'|| data[i] == 'i' || data[i] == 'o' || data[i] == 'u') { // 모음이라면
                vowels--;	// 모음 count -1 하기
                result[cnt] = data[i];	// 해당 알파벳 저장
                pw(i+1, cnt+1);	// 재귀 함수
                vowels++;	// 모음 count +1 해서 복귀
            }
            else {
                consonants--;	// 자음이라면 count -1 하기
                result[cnt] = data[i]; // 해당 알파벳 저장
                pw(i+1, cnt+1); // 재귀 함수
                consonants++; // 자음 count +1 해서 복귀
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        data = new char[C]; 	// 문자 종류
        result = new char[L];	// 정답 넣을 배열

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++)
            data[i] = st.nextToken().charAt(0);

        vowels = 1; consonants = 2; //최소 한 개의 모음, 최소 두 개의 자음
        Arrays.sort(data); // 정렬 (증가하는 순서)
        pw(0,0);	// 가능한 암호 찾는 재귀 함수
        System.out.println(sb.toString()); // 찾은 암호 모두 출력
    }

    // Baekjoon Online Judge 6987 월드컵
    public static class bj6987 {
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
}
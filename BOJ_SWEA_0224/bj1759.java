package algorithm_java.BOJ_SWEA_0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
}
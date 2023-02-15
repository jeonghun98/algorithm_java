package algorithm_java.BOJ_0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1244 {

    static int[] data;
    static int switch_c; //스위치 개수

    public static void change_turn_on_off(int i) { //index의 스위치 변환
        if(data[i] == 0) data[i] = 1;
        else data[i] = 0;
    }

    public static void turn_on_off(int s, int n) {
        if(s == 1) {		// 남학생
            int i = 2; int temp = n;
            while(temp <= switch_c) {	// 배수로 돌려가면서 스위치 변환
                change_turn_on_off(temp);
                temp = n * i; i++;
            }
        }
        else {
            int left = n-1;
            int right = n+1;
            change_turn_on_off(n);	// 자기자신 스위치 변환
            while(true) {
                if(left < 1 || right > switch_c) break;
                if(data[left] == data[right]) {	//왼쪽과 오른쪽 모두 대칭이라면 스위치 변환
                    change_turn_on_off(left);
                    change_turn_on_off(right);
                    left--; right++;			//변환 후 왼쪽, 오른쪽 한번 더 이동
                }
                else
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        switch_c = Integer.parseInt(br.readLine());
        data = new int[switch_c+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= switch_c; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            turn_on_off(student,number);	//스위치 상태 변환 메소드
        }

        StringBuilder sb = new StringBuilder();	// 출력
        for(int i = 1; i <= switch_c; i++) {
            if(i % 20 == 0) sb.append(data[i] + "\n");
            else sb.append(data[i] + " ");
        }
        System.out.println(sb.toString());
    }
}



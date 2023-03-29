package algorithm_java.BOJ_SWEA_0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//컨베이어 벨트 위의 로봇
public class bj20055{

    static int n, k;
    static int belt[]; //벨트 내구도
    static boolean robot[]; //로봇 유무 check
    static int belt_index; //벨트의 시작 index

    public static void rotation() { // 벨트와 로봇 이동
        belt_index = belt_index == 0? n * 2-1 : belt_index - 1; // 벨트 시작 위치를 이동

        for(int i = n-2; i >= 0; i--) // 로봇 이동
            robot[i+1] = robot[i];
        robot[0] = false;
    }

    public static void move() { // 로봇이 이동 (이동할 수 있다면)

        robot[n-1] = false;
        for(int i = n-2; i > 0; i--) { // 로봇 이동 -> 현재 로봇이 있고 다음위치가 비어 있고 다음 벨트에 내구도 1이상이라면
            if(robot[i] && !robot[i+1] && belt[(belt_index+i+1)%(n*2)]-- > 0) {
                robot[i+1] = true;
                robot[i] = false;
            }
        }
    }

    public static void up_robot() { // 1번칸에 로봇 올리기

        if(belt[belt_index]-- > 0)
            robot[0] = true;

    }

    public static boolean check() { // 내구도 0인 칸의 개수 확인
        int count = 0;

        for(int i = 0; i < n*2; i++) {
            if(belt[i] < 1) count++;
            if(count == k) return false;
        }

        return true;
    }

    public static int work() { // 벨트 일하는 횟수 count
        int count = 1;

        while(true) {
            rotation();
            move();
            up_robot();
            if(!check()) return count;
            count++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        belt = new int[n*2];
        robot = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n*2; i++) {
            belt[i] = (Integer.parseInt(st.nextToken()));
        }

        belt_index = 0;
        System.out.println(work());
    }
}
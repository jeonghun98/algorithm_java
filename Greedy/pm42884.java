package algorithm_java.Greedy;
import java.util.*;

// programmers 42884 단속카메라
class pm42884 {
    public static int solution(int[][] routes) {
        int answer = 1;

        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        int cur = routes[0][1];

        for(int r[] : routes) {
            if(r[0] > cur || r[1] < cur) {
                cur = r[1];
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println((solution(new int[][] {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}}))); // 2
        System.out.println((solution(new int[][] {{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}}))); // 2
    }
}
package algorithm_java.Floyd_Warshall;
import java.util.*;

// programmers 49191 순위
public class pm49191 {
    public static int solution(int n, int[][] results) {
        int answer = 0;
        int data[][] = new int[n+1][n+1];

        for(int r[] : results) {
            data[r[0]][r[1]] = 1;
            data[r[1]][r[0]] = -1;
        }

        for(int k = 1; k < n+1; k++) {
            for(int i = 1; i < n+1; i++) {
                for(int j = 1; j < n+1; j++) {
                    if(data[i][k] == 1 && data[k][j] == 1) {
                        data[i][j] = 1;
                        data[j][i] = -1;
                    }
                }
            }
        }

        for(int i = 1; i < n+1; i++) {
            int cnt = 1;
            for(int j = 1; j < n+1; j++) {
                if(data[i][j] != 0) cnt++;
            }
            if(cnt == n) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[][] {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}})); // 2
    }
}

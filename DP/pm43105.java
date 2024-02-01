package algorithm_java.DP;
import java.util.*;

// programmers 43105 정수 삼각형
public class pm43105 {
    public static int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i+1; j++) {
                int left = 0, right = 0;

                if(j > 0) left = triangle[i-1][j-1]; // 왼쪽 줄 x
                if(i > j) right = triangle[i-1][j]; // 대각선 줄 x

                triangle[i][j] += Math.max(left, right);
                if(i == n-1) answer = Math.max(answer, triangle[i][j]);
            }
        }
        return answer;
    }

    public static int solution2(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;

         for(int i = 1; i < n; i++) {
             triangle[i][0] += triangle[i-1][0]; // 왼쪽 세로줄하고 대각선줄은 위에서만 내려옴
             triangle[i][i] += triangle[i-1][i-1];
             for(int j = 1; j < i; j++) {
                 triangle[i][j] = triangle[i][j] + Math.max(triangle[i-1][j-1], triangle[i-1][j]);
             }
         }

         for(int num : triangle[n-1])
             answer = Math.max(answer, num);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}})); // 30
        System.out.println(solution2(new int[][] {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}})); // 30
    }
}

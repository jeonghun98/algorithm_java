package algorithm_java.String;
import java.util.*;

// programmers pm12939 최댓값과 최솟값
public class pm12939 {
    public static String solution(String s) {
        String[] numbers = s.split(" ");

        int n, max, min;
        max = min = Integer.parseInt(numbers[0]);

        for(String number : numbers) {
            n = Integer.parseInt(number);
            max = max < n ? n : max;
            min = min > n ? n : min;
        }

        return min + " " + max;
    }
    public static void main(String[] args) {
        System.out.println(solution("1 2 3 4")); // 1 4
        System.out.println(solution("-1 -2 -3 -4")); // -4 -1
        System.out.println(solution("-1 -1")); // -1 -1
    }
}

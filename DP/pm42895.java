package algorithm_java.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

// programmers 42895 N으로 표현
public class pm42895 {
    static HashSet<Integer> set[];
    public static int solution(int N, int number) {
        set = new HashSet[9];
        int num = N;
        for(int i = 1; i < 9; i++) {
            set[i] = new HashSet<>();
            set[i].add(num);
            num = num*10 + N;
        }

        for(int i = 2; i < 9; i++) {        // N 사용 횟수(2 ~ 8)
            for(int j = 1; j < i; j++) {    // 2 : 1+1, 3 : 1+2, 2+1, 4 : 1+3, 2+2, 3+1, 5 : ...
                for(int s1 : set[j]) {      // i + (i-j) => set[i] update
                    for(int s2 : set[i-j]) {
                        set[i].add(s1+s2);
                        set[i].add(s1*s2);
                        if(s1 > s2) set[i].add(s1-s2);
                        if(s2 != 0) set[i].add(s1 / s2);
                    }
                }
            }
        }

//        for(int i = 1; i < 9; i++) {
//            ArrayList<Integer> list = new ArrayList<>(set[i]);
//            System.out.println(list);
//        }

        for(int i = 1; i < 9; i++) {
            if(set[i].contains(number)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 12)); // 4
        System.out.println(solution(5, 31168)); // -1
        System.out.println(solution(5, 11)); // 3
    }
}

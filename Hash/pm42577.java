package algorithm_java.Hash;

import java.util.*;

// programmers 42577 전화번호 목록
public class pm42577 {
    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        HashMap<String, Integer> set = new HashMap<>();
        for(String p : phone_book) {
            set.put(p, 1);
        }

        for(String p : phone_book) {
            int len = p.length();
            for(int i = 1; i < len; i++) {
                if(set.getOrDefault(p.substring(0, i), 0) > 0) {
                    return false;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[] {"119", "97674223", "1195524421"}));   // false
        System.out.println(solution(new String[] {"123", "456", "789"}));               // true
        System.out.println(solution(new String[] {"12", "123", "1235", "567", "88"}));  // false
        System.out.println(solution(new String[] {"97674223", "1195524421", "119"}));   // false
    }
}

package algorithm_java.Hash;

import java.util.*;

// programmers 42576 완주하지 못한 선수
public class pm42576 {
    public static String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> map = new HashMap<>();
        for(String item : participant) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        for(String item : completion) {
            map.put(item, map.get(item) - 1);
        }

        for(String key : map.keySet()) {
            if(map.get(key) > 0) {
                answer = key;
                break;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"leo", "kiki", "eden"}, new String[] {"eden", "kiki"}));
        System.out.println(solution(new String[] {"marina", "josipa", "nikola", "vinko", "filipa"}, new String[] {"josipa", "filipa", "marina", "nikola"}));
    }
}

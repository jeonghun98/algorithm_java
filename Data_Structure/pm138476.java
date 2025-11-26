package algorithm_java.Data_Structure;

import java.util.*;

// programmers 138476 귤 고르기
class pm138476 {
    public static int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int idx : tangerine) {
            map.put(idx, map.getOrDefault(idx, 0) + 1);
        }

//         List<Integer> keys = new ArrayList<>(map.keySet());
//         keys.sort((o1, o2) -> map.get(o2)-map.get(o1));

//         for(Integer key : keys) {
//             k -= map.get(key);
//             answer++;
//             if(k <= 0) break;
//
        List<Integer> values = new ArrayList<>(map.values());
        values.sort(Collections.reverseOrder());

        for(Integer value : values) {
            k -= value;
            answer++;
            if(k <= 0) break;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(6, new int[] {1,3,2,5,4,5,2,3}));
        System.out.println(solution(4, new int[] {1,3,2,5,4,5,2,3}));
        System.out.println(solution(2, new int[] {1,1,1,1,2,2,2,3}));
    }
}
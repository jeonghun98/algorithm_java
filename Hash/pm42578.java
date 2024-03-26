package algorithm_java.Hash;

import java.util.HashMap;
import java.util.Iterator;

// programmers 42578 의상
public class pm42578 {
    public static int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for(String[] clothe: clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0)+1);
        }

//        의상 조합 = (의상 수 + 1) * (의상 수 + 1) * (의상 수 + 1) -1
//        의상수 + 1 : 그 의상을 입지 않았을 경우의 수
//        -1 : 다 입지 않는 경우의 수는 없음 (최소 한 개의 의상 착용)
         for(String key : map.keySet()) {
             answer *= (map.get(key)+1);
         }

//        Iterator<Integer> value = map.values().iterator();
//        while(value.hasNext()) {
//            answer *= value.next().intValue()+1;
//        }

        return answer-1;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}})); // 5
        System.out.println(solution(new String[][]{{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}));             // 3
    }
}

package algorithm_java.Data_Structure;
import java.util.*;

// programmers 42628 이중우선순위큐 = Baekjoon Online Judge 7662 이중 우선순위 큐
public class pm42628 {
    public static int[] solution(String[] operations) {
        return doublePriorityQueue(operations);
    }
    public static int[] doublePriorityQueue(String[] operations) {
        PriorityQueue<Integer> minQ = new PriorityQueue<>(); // 오름차순
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < operations.length; i++) {
            int num = Integer.parseInt(operations[i].split(" ")[1]);

            if(operations[i].charAt(0) == 'I') {
                map.put(num, map.getOrDefault(num, 0) + 1);
                minQ.add(num);
                maxQ.add(num);
            } else {
                if(map.size() == 0) continue;

                if(num == 1) delete(maxQ, map);
                else delete(minQ, map);
            }
        }

        if(map.size() == 0) return new int[] {0,0};
        return new int[] {delete(maxQ, map), delete(minQ, map)};
    }
    public static int delete(PriorityQueue<Integer> pq, Map<Integer, Integer> map) {
        while(true) {
            int num = pq.poll();
            int cnt = map.getOrDefault(num, 0);
            if(cnt == 0) continue;

            if(cnt == 1) map.remove(num);
            else map.put(num, cnt-1);
            return num;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[] {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"}))); // [0,0]
        System.out.println(Arrays.toString(solution(new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}))); // [333, -45]
    }
}

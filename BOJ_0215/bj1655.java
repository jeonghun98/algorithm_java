package algorithm_java.BOJ0215;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class bj1655 {
    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 우선순위 큐를 두개 사용 -> 내림차순 큐는 중앙값을 포함한 이하 값, 오름차순 큐는 중앙값 초과 값
        PriorityQueue<Integer> min_pq = new PriorityQueue<>(); // 오름차순
        PriorityQueue<Integer> max_pq = new PriorityQueue<>(Collections.reverseOrder()); //내림차순

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            if(max_pq.size() == min_pq.size()) { //내림차순 큐부터 넣어줌
                max_pq.add(Integer.parseInt(br.readLine()));
            }
            else
                min_pq.add(Integer.parseInt(br.readLine()));

            if(!min_pq.isEmpty()) {	//오름차순 큐가 비어 있지 않고
                if(max_pq.peek() > min_pq.peek()) {	//중앙값이 오름차순 보다 크다면 서로 값 변경
                    int temp0 = min_pq.poll();
                    int temp1 = max_pq.poll();
                    max_pq.add(temp0);
                    min_pq.add(temp1);
                }
            }
            sb.append(max_pq.peek() + "\n");
        }System.out.print(sb.toString());
    }
}

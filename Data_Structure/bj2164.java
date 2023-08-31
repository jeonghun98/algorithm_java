package algorithm_java.Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
// Baekjoon Online Judge 2164 카드2
public class bj2164 {

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //N장의 카드

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++)	//N개의 카드를 순서대로 넣는다
            q.offer(i);

        while(q.size() > 1) {	//카드가 한개 남을때까지 while반복
            q.remove();			//제일 위에 카드 버리기
            q.offer(q.poll());	//그 다음카드를 빼서 뒤로 옮긴다
        }
        System.out.println(q.peek());	//한개 남은 카드 출력
    }
}

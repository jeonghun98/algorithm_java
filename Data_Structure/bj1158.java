package algorithm_java.Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// Baekjoon Online Judge 1158 요세푸스 문제
public class bj1158 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());	//n
        int k = Integer.parseInt(st.nextToken());	//k

        Queue<Integer> q = new ArrayDeque<Integer>();
        for(int i = 1; i <= n; i++) q.add(i);	//1~n add

        sb.append("<");
        while(q.size() != 1) {	// 1~n-1개 출력
            for(int i = 1; i < k; i++) {
                q.offer(q.poll());	//k번째까지 빼고 넣기
            }
            sb.append(q.poll() + ", ");//k번째 출력
        }
        sb.append(q.poll() + ">");	//마지막 1개출력
        System.out.println(sb.toString());
    }
}



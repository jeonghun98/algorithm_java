package algorithm_java.Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

// Baekjoon Online Judge 2493 탑
public class bj2493_v2025 {
    static int[] arr;
    static int n;

    public static class Top implements Comparable<Top>{
        int idx, num;
        public Top(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
        public int compareTo(Top o) {
            return o.num - num;
        }
    }

    public static StringBuilder laser() {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Top> pq = new PriorityQueue<>(Collections.reverseOrder());

        int[] result = new int[n];
        for(int i = n-1; i >= 0; i--) {
            while(!pq.isEmpty()) {
                if(arr[i] < pq.peek().num) break;
                Top now = pq.poll();
                result[now.idx] = i+1;
            }
            pq.add(new Top(i,arr[i]));
        }

        while(!pq.isEmpty()) {
            Top top = pq.poll();
            arr[top.idx] = 0;
        }

        for(int i = 0; i < n; i++) {
            sb.append(result[i]).append(" ");
        }

        return sb;
    }

    public static StringBuilder laser2() {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for(int i = n-1; i > 0; i--) {
            if(arr[i-1] >= arr[i]) {
                result[i] = i;
                while(!stack.isEmpty()) { // stack에 내림차순으로 저장됨
                    if(arr[i-1] >= arr[stack.peek()]) result[stack.pop()] = i;
                    else break;
                }
            } else {
                stack.push(i);
            }
        }

        for(int i = 0; i < n; i++)
            sb.append(result[i]).append(" ");

        return sb;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

//        System.out.println(laser());
        System.out.println(laser2());
    }
}


package algorithm_java.BOJ_SWEA_0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class swea14510 {
    static int n, max_n;
    static int data[];
    static Stack<Integer> even, odd;

    public static int tree() {
        int day = 0;

        if(even.size() == 0 && odd.size() == 0) return day;

        while(even.size() != 0 || odd.size() != 0) {
            day++;
            if(day % 2 == 1) { // 홀수 날짜
                if(odd.size() != 0) // 홀수 스택이 비어 있지 않다면 해당 나무 키우고 짝수에 넣기
                    even.push(odd.pop()-1);

                else { // 홀수 스택이 비어있는데 짝수 하루로 안끝나면 홀수날짜도 사용
                    if(even.size() >= 2 || (int)(even.peek() / 2) >= 2)
                        odd.push(even.pop() - 1);
                }
            }
            else { // 짝수 날짜
                if(even.size() != 0) // 짝수 스택이 비어 있지 않다면 나무 키우고 다시 짝수에 넣기
                    even.push(even.pop()-2);

                else { // 짝수 스택이 비어있는데 홀수 스택이 2이상 자라야 하는 나무가 있다면 키우기
                    if(odd.size() != 0 && odd.peek() > 1)
                        odd.push(odd.pop()-2);
                }
            }

            // 나무가 max까지 다 자랐다면 pop
            if(odd.size() != 0 && odd.peek() == 0) odd.pop();
            if(even.size() != 0 && even.peek() == 0) even.pop();
        }
        return day;
    }

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            data = new int[n];

            st = new StringTokenizer(br.readLine());

            max_n = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++) {
                data[i] = Integer.parseInt(st.nextToken());
                max_n = Math.max(max_n, data[i]);
            }


            odd = new Stack<>();
            even = new Stack<>();
            for(int i = 0; i < n; i++) {
                int tree_height = max_n - data[i]; // 키워야 되는 나무의 높이
                if(tree_height <= 0) continue;

                if(tree_height % 2 == 0)
                    even.add(tree_height);  //키워야 되는 나무의 높이가 짝수라면
                else
                    odd.add(tree_height);   //키워야 되는 나무의 높이가 홀수라면
            }

            sb.append("#" + tc + " " + tree() + "\n");

        }System.out.println(sb.toString());
    }
}
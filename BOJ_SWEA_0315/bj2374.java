package algorithm_java.BOJ_SWEA_0315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj2374 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        long cnt = 0;
        int max = 0 ;
        for(int i = 0; i < n; i++){
            int data = Integer.parseInt(br.readLine());
            max = Math.max(max, data);

            if(stack.isEmpty()){
                stack.push(data);
            }
            else{
                if(stack.peek() > data) {
                    stack.pop();
                    stack.add(data);
                }

                else if(stack.peek() < data) {
                    cnt += data - stack.pop();
                    stack.push(data);
                }
            }
        }
        while(!stack.isEmpty()) {
            cnt += max - stack.pop();
        }
        System.out.println(cnt);
    }
}

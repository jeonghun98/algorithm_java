package algorithm_java.BOJ_SWEA_0418;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// Baekjoon Online Judge 1918 후위 표기식
public class bj1918 {
    public static String postfix(char data[]){
        String result = "";
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < data.length; i++) {
            char now = data[i];

            if('A' <= now && now <= 'Z') {
                result += now;
            }
            else {
                if(now == '(') { // (
                    stack.add(now);
                }else if(now == ')') { // )
                    while (!stack.isEmpty()) {
                        char tmp = stack.pop();
                        if (tmp == '(') break;
                        else result += tmp;
                    }
                }else if(now == '*' || now == '/') { // *, /
                    while (!stack.isEmpty()) {
                        if (stack.peek() == '(' || stack.peek() == '+' || stack.peek() == '-') break;
                        else result += stack.pop();
                    }
                    stack.add(now);
                }else {
                    while (!stack.isEmpty()) {
                        if (stack.peek() == '(') break;
                        else result += stack.pop();
                    }
                    stack.add(now);
                }
            }
        }
        while(!stack.isEmpty())
            result += stack.pop();

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char data[] = br.readLine().toCharArray();
        System.out.println(postfix(data));
    }
}

package algorithm_java.Stack;
import java.util.*;

// programmers 76502 괄호 회전하기
public class pm76502 {
    public static int solution(String s) {
        int answer = 0;

        Stack<Character> stack = new Stack<>();
        int idx, len = s.length();
        char c, tmp;
        boolean check = true;

        for(int i = 0; i < len; i++) {
            check = true;
            for(int j = 0; j < len; j++) {
                idx = (i + j) % len;
                c = s.charAt(idx);
                if(c == '[' || c == '(' || c == '{') { stack.push(c); }
                else {
                    if(stack.isEmpty()) {check = false; break;}
                    if(stack.peek() == '[' && c == ']') stack.pop();
                    else if(stack.peek() == '(' && c == ')') stack.pop();
                    else if(stack.peek() == '{' && c == '}') stack.pop();
                    else {check = false; break;}
                }
            }
            if(check && stack.isEmpty()) answer++;
        }
        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("[](){}")); // 3
        System.out.println(solution("}]()[{")); // 2
        System.out.println(solution("[)(]"));   // 0
        System.out.println(solution("}}}"));    // 0
    }
}

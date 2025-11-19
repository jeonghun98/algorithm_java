package algorithm_java.Stack;

import java.util.Stack;

// programmers 131704 택배상자
public class pm131704 {
    public static int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        int n = order.length;
        int idx = 0;

        for(int i = 1; i <= n; i++) {
            stack.push(i);
            while(!stack.isEmpty()) {
                if(stack.peek() == order[idx]) {
                    stack.pop();
                    idx++;
                } else break;
            }
        }
        return idx;
    }
    public static void main(String[] args) {
        System.out.println(solution(new int[] {4,3,1,2,5}));
        System.out.println(solution(new int[] {5,4,3,2,1}));
        System.out.println(solution(new int[] {3,2,1,4,5}));
    }
}

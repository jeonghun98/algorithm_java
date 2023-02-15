package algorithm_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj6198 {
    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long result = 0;
        Stack<Integer> stack = new Stack<>();

        int before = Integer.parseInt(br.readLine()); // 1번 관리인
        for(int i = 1; i < n; i++) {
            int now = Integer.parseInt(br.readLine()); //2~n번 관리인

            // 해당 스택은 밑의 값이 큰 구조(아래로 오름차순)
            while(!stack.isEmpty()) { //스택에 있으면 제일 위의 값과 비교
                if(stack.peek() > now)	{	//제일 위의 값이 현재값보다 크면 사이즈만큼 결과에 더함
                    result += stack.size();
                    break;					//그리고 break
                }
                else
                    stack.pop();	//제일 위에 값보다 큰값이면(옥상 확인x) -> pop
            }
            if(before > now) {	//전의 값이 현재 값보다 크면 결과+1하고 stack에 push
                result++;
                stack.push(before);
            }
            before = now;
        }
        System.out.println(result);
    }
}
package algorithm_java.Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
// Baekjoon Online Judge 2493 탑
public class bj2493 {
    static int data[];	// 탑의 높이가 저장된 배열
    static int n;

    public static int[] getlist() {
        Stack<Integer> stack = new Stack<>();
        int result[] = new int[n];	// 정답 넣을 배열

        for(int i = n-1; i > 0; i--) {
            if(data[i-1] > data[i]) {	//만약 현재의 탑보다 앞의 탑이 더 크다면
                result[i] = i;			//해당 index 저장
                while(!stack.isEmpty()) {	// 그리고 stack에 저장된 값도 비교
                    if(data[i-1] >= data[stack.peek()]) {	//stack의 peek값보다 앞의 탑이 더 크다면
                        result[stack.pop()] = i;	// stack pop하고 해당 index 저장
                    }
                    else break;
                }
            }
            else {
                stack.push(i); //앞의 탑이 더 크지 않다면 stack에 push
            }
        }return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());	//n개의 탑
        StringTokenizer st = new StringTokenizer(br.readLine());

        data = new int[n];
        for(int i = 0; i < n; i++) {	//탑의 높이 저장
            data[i] = Integer.parseInt(st.nextToken());
        }

        for(int i : getlist()) {
            sb.append(i + " ");
        }
        System.out.println(sb.toString());
    }
}


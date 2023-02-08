package algorithm_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class bj2493 {
    static int data[];
    static int n;

    public static int[] getlist() {
        Stack<Integer> stack = new Stack<>();
        int result[] = new int[n];

        for(int i = n-1; i > 0; i--) {
            if(data[i-1] > data[i]) {
                result[i] = i;
                while(!stack.isEmpty()) {
                    if(data[i-1] >= data[stack.peek()]) {
                        result[stack.pop()] = i;
                    }
                    else break;
                }
            }
            else {
                stack.push(i);
            }
        }return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        data = new int[n];
        for(int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        for(int i : getlist()) {
            sb.append(i + " ");
        }
        System.out.println(sb.toString());
    }
}
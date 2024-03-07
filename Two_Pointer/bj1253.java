package algorithm_java.Two_Pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 1253 좋다
public class bj1253 {
    static int n;
    static int data[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        data = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(data);
        int result = cntGOOD();
        System.out.println(result);
    }

    private static int cntGOOD() {
        int result = 0;

        for(int i = 0; i < n; i++) {
            int left = 0, right = n-1;

            while(left < right) {
                int sum = data[left] + data[right];
                if(data[i] > sum) left++;
                else if(data[i] < sum) right--;
                else {
                    if(left == i) left++;
                    else if(right == i) right--;
                    else {
                        result++;
                        break;
                    }
                }
            }
        }
        return result;
    }
}

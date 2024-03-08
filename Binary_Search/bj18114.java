package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 18114 블랙 프라이데이
public class bj18114 {
    static int n, c;
    static int data[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        data = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n;i++) {
            data[i] = Integer.parseInt(st.nextToken());
            if(data[i] == c) {
                // 해당 물건으로만 무게 c가 가능하면 출력
                System.out.println(1);
                System.exit(0);
            }
        }

        Arrays.sort(data);
        boolean result = false;

        // 정렬 후 c 이상의 값 필요 x
        int maxIdx = Arrays.binarySearch(data, c);
        if(maxIdx < 0) maxIdx = Math.abs(maxIdx)-1;

        // i = 0 부터, j는 c값 이하에서 시작
        for(int i = 0, j = maxIdx-1; i < j; ) {
            int sum = data[i] + data[j];
            if(sum == c) {
                result = true;
                break;
            } else if(sum > c) {
                j--;
            } else {
                // i+1 ~ j-1 사이에서 c-sum 값 탐색
                int idx = Arrays.binarySearch(data, i+1, j, c-sum);
                if(idx > i && idx < j) { // 해당 값이 i+1 ~ j-1 사이에 있다면 조건 만족
                    result = true;
                    break;
                }
                i++;
            }
        }
        System.out.println(result ? 1 : 0);
    }
}

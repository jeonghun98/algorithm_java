package algorithm_java.BOJ_SWEA_0601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14003_2 {
    public static int lower_bound(int[] buffer, int left, int right, int target) { // binarySearch 변형 구현 -> LIS(음수 고려 x)
        while (left < right) {
            int mid = (left + right) / 2;
            if (buffer[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int arr[] = new int[n+1];
        int dp[] = new int[n + 1];
        int idxList[] = new int[n+1];

        int size = 0;
        dp[size] = Integer.MIN_VALUE;

        for(int i = 1; i < n+1; i++) {
            int data = Integer.parseInt(st.nextToken());
            arr[i] = data;
            if(dp[size] < data) { // 현재 dp의 마지막 숫자보다 크면 dp 추가
                dp[++size] = data;
                idxList[i] = size;
            } else {
                int tmp = Arrays.binarySearch(dp, 0,size, data); // 이분탐색
                if (tmp < 0) { // 갱신값이라면
                    tmp = (-1) * (tmp + 1);
                    dp[tmp] = data; // 해당 index의 dpNum을 작은 숫자로 갱신
                }
                idxList[i]= tmp;

//                int idx = lower_bound(dp, 0, size, data);
//                dp[idx] = data;
//                idxList[i] = idx;

            }
        }
        sb.append(size + "\n");// 현재 가르키는 size가 '가장 긴 증가하는 부분 수열'의 값

        int result[] = new int[size+1];
        int ptr = n;
        // n -> 1로 감소하면서 idxList 에 저장해놓은 값과 현재값(size->1)을 비교하고 맞다면 출력
        for(int i = size; i > 0; i--) {
            while(idxList[ptr] != i) ptr--;
            result[i] = arr[ptr--];
        }
        for(int i = 1; i < size+1; i++) {
            sb.append(result[i] + " ");
        }
        System.out.println(sb.toString());
    }
}

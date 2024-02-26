package algorithm_java.Binary_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Baekjoon Online Judge 14003 가장 긴 증가하는 부분 수열 5
public class bj14003_3 {
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

        int arr[] = new int[n];
        int dp[] = new int[n];
        int idxList[] = new int[n];

        int size = 0;
        dp[size] = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            int data = Integer.parseInt(st.nextToken());
            arr[i] = data;
            int tmp = Arrays.binarySearch(dp, 0,size, data); // 이분탐색
            if (tmp < 0) { // 갱신값이라면
                tmp = (-1) * (tmp + 1);
                dp[tmp] = data; // 해당 index의 dpNum을 작은 숫자로 갱신
            }
            if(tmp == size) size++;
            idxList[i]= tmp;

//            int idx = lower_bound(dp, 0, size, data);
//            dp[idx] = data;
//            idxList[i] = idx;

        }
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(dp));
//        System.out.println(Arrays.toString(idxList));

        sb.append(size + "\n");// 현재 가르키는 size가 '가장 긴 증가하는 부분 수열'의 값

        int result[] = new int[size];
        int ptr = n-1;
        // n -> 1로 감소하면서 idxList 에 저장해놓은 값과 현재값(size->1)을 비교하고 맞다면 출력
        for(int i = size-1; i >= 0; i--) {
            while(idxList[ptr] != i) ptr--;
            result[i] = arr[ptr--];
        }
        for(int r : result) {
            sb.append(r + " ");
        }
        System.out.print(sb.toString());
    }
}

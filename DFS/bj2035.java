package algorithm_java.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
// Baekjoon Online Judge 2035 증가수열
public class bj2035 {
    static char data[];
    static int len;
    static long result;
    public static long[] deepCopy(long dp[]) {
        long tmpDp[] = Arrays.copyOf(dp, len+1);
        return tmpDp;
    }
    public static void dfs(int idx, long dp[], int size) {
        if(idx == len) {
            result = Math.min(dp[size-1],result);
            return;
        }

        long num = 0;
        for(int i = idx; i < len; i++) {
            num = num * 10 + (data[i] - '0');
            if(num > result) continue;
            if(num < 0) break;

            int tmp = Arrays.binarySearch(dp, 0, size, num);
            if(tmp >= 0) continue;

            tmp = Math.abs(tmp) - 1;

            if(size == tmp) {
                long tmpDp[] = deepCopy(dp);
                tmpDp[tmp] = num;
                dfs(i+1, tmpDp, size+1);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        data = num.toCharArray();
        len = data.length;

        long dp[] = new long[len+1];
        int size = 0;
        dp[size] = -1;

        result = Long.MAX_VALUE;
        dfs(0,dp,size);
        System.out.println(result == Long.MAX_VALUE ? num : result);
    }
}
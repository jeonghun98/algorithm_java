package algorithm_java.BOJ_SWEA_0228;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14888 {

    static int n;
    static int data[];
    static int operation[];
    static int result_max, result_min;

    public static void dfs(int cnt, int num) {
        if(cnt == n) {
            result_max = Math.max(result_max, num);
            result_min = Math.min(result_min, num);
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(operation[i] == 0) continue;

            operation[i]--;

            if(i == 0) // +
                dfs(cnt+1, num+data[cnt]);

            if(i == 1) // -
                dfs(cnt+1, num-data[cnt]);

            if(i == 2) // *
                dfs(cnt+1, num*data[cnt]);

            if(i == 3) // /
                dfs(cnt+1, (int)num/data[cnt]);

            operation[i]++;
        }
    }


    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        data = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        operation = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        result_max = Integer.MIN_VALUE;
        result_min = Integer.MAX_VALUE;

        dfs(1,data[0]);

        System.out.println(result_max);
        System.out.println(result_min);
    }
}
package algorithm_java.BOJ_SWEA_0405;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj15961 {
    static int n, d, k, c;
    static int sushi_cnt[];
    static int arr[];

    public static int rotation(int tmp) {
        int cnt = tmp;
        int result = tmp;

        for(int i = k; i < n + k; i++){
            int j = i - k;
            if(--sushi_cnt[arr[j]] == 0) cnt--; // k 앞의 초밥 감소 => 해당 종류의 초밥이 0으로 된다면 cnt 감수
            if(sushi_cnt[arr[i >= n ? i % n : i]]++ == 0) cnt++; // 현재 값 증가 => 증가 전에 종류가 cnt 안되어있다면 cnt 증가
            result = Math.max(cnt, result);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];

        sushi_cnt = new int[d+1];
        sushi_cnt[c]++;
        int cnt = 1;

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(i < k) {
                if(sushi_cnt[arr[i]] == 0) cnt++;
                sushi_cnt[arr[i]]++;
            }
        }
        System.out.println(rotation(cnt));
    }
}
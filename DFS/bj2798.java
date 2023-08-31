package algorithm_java.DFS;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 2798 블랙잭
public class bj2798 {
    static int result = Integer.MIN_VALUE, n, m;
    static int card[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        card = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++)
            card[i] = Integer.parseInt(st.nextToken());

        Blackjack(0,0,0);
        System.out.println(result);
    }

    public static void Blackjack(int start, int cnt, int total) { // 조합 재귀함수
        if(total > m) return;	// m을 넘을 때 return

        if(cnt == 3) {	// 3장의 카드 -> max값
            result = Math.max(total, result);
            return;
        }

        for(int i = start; i < n; i++)	// 시작점, count 증가시키고 카드 값 추가해서 -> 재귀
            Blackjack(i+1, cnt+1, total + card[i]);
    }
}

package algorithm_java.DFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 15649 N과 M (1)
public class bj15649 {
    static int n, m;
    static boolean isSelected[];	//사용한 index
    static int numbers[];			//수열 저장
    static StringBuilder sb = new StringBuilder();

    public static void per(int now_cnt) {
        if (m == now_cnt) {
            for(int i = 0; i < numbers.length; i++) {
                sb.append(numbers[i]+1 +" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 0; i < isSelected.length; i++) {
            if(isSelected[i]) continue;		//사용된 index => continue
            isSelected[i] = true;			//사용 x => true 변경 후 수열에 저장
            numbers[now_cnt] = i;
            per(now_cnt+1);					//count 증가 후 재귀호출
            isSelected[i] = false;			//재귀 호출 후 index 사용 false로 변경
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        isSelected = new boolean[n];
        numbers = new int[m];
        per(0);
        System.out.println(sb.toString());
    }
}



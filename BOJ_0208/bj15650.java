package algorithm_java.BOJ_0208;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj15650 {
    static int n, m;
    static int[] numbers;	//m개의 크기의 배열 => 조합이 저장될 배열
    static StringBuilder sb = new StringBuilder();

    public static void combination(int cnt,int start) {	//cnt => 현재까지 뽑은 조합 원소 개수, start => 조합 시작 index
        if(cnt == m) {	//조합 생성완료
            for(int i : numbers)
                sb.append(i + " ");
            sb.append("\n");
            return;
        }
        for(int i = start; i <= n; i++) {
            numbers[cnt] = i;
            combination(cnt+1, i+1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[m];
        combination(0,1);
        System.out.println(sb.toString());
    }
}


package algorithm_java.BOJ_0214;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14501 {
    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int t[] = new int[n+1]; //시간
        int p[] = new int[n+1];	//금액
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }


        int max_value = 0;	//현재의 최댓값
        for(int i = n-1; i >= 0; i--) {	// 뒤 -> 앞으로
            int end_time = t[i] + i;	//현재 상담이 끝나고 그 뒤에 상담시간도 확인

            if(end_time <= n) {	// 뒤에 상담시간이 있다면
                p[i] = Math.max(p[i] + p[end_time], max_value); //해당 상담시간과 현재의 상담시간 vs 현재 금액의 최댓값
                max_value = p[i];	//최댓값 update
            }

            else
                p[i] = max_value;	//뒤에 상담 x -> 최댓값으로 update
        }
        System.out.println(max_value);
    }
}

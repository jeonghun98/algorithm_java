package algorithm_java.Binary_Search;
import java.io.*;
import java.util.*;

// Softeer 6247 [HSAT 7회 정기 코딩 인증평가 기출] 자동차 테스트
public class soft6247 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int data[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(data);

        for(int i = 0; i < q; i++) {
            int num = Integer.parseInt(br.readLine());
            int tmp = Arrays.binarySearch(data, 0, n, num);
            if(tmp < 0) sb.append(0 + "\n");
            else sb.append(tmp * (n-tmp-1) + "\n");
        }
        System.out.print(sb.toString());
    }
}

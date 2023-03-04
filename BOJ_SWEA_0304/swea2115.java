package algorithm_java.BOJ_SWEA_0304;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea2115 {
    static int n,m,c;
    static int data[][];
    static int honey_m[];

    public static int honey_collection() { // 벌꿀 수집
        int result = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n - m + 1; j++) {
                int a = total_honey(i,j); // a 일꿀 벌꿀 채취
                int b = another_collection(i,j); // b 일꿀의 최대 벌꿀 채취
                result = Math.max(result, a + b); // 최대 수익만 저장
            }
        }
        return result;
    }

    public static int another_collection(int x, int y) { // 다른 한명의 일꾼 -> a 일꾼의 뒤를 모두 확인

        if(y + 2*m > n) { // 해당 열으로 m 만큼 수집 x -> 다음줄
            y = 0; x++;
        } else {		// 전 일꿀에서 m뒤에서 채취
            y += m;
        }

        int honey = 0;
        for(int i = x; i < n; i++) { // a 일꾼 뒤에 모든 경우 확인
            for(int j = i == x ? y : 0; j < n - m + 1; j++) {
                honey = Math.max(honey, total_honey(i,j)); // 해당 위치에서 최대 수익 찾기 -> 최대 수익만 저장
            }
        }
        return honey;
    }

    public static int total_honey(int x, int y) { // 꿀 채취
        for(int i = 0; i< m; i++)
            honey_m[i] = data[x][y+i]; // 해당 위치에서 채취할 수 있는 꿀 m개를 배열에 저장
        return combi(); // 조합으로 최대 수익 return
    }

    public static int combi() { // 조합 => 현재 honey_m에 저장된 m개의 꿀에서 최대 수익 계산

        int honey = 0; // 최대 수익
        int sum, value; // 꿀의 양, 수익

        for(int i = 1; i < (1<<m); i++) { //mC(1~m) 을 모두 확인
            sum = 0; value = 0;
            for(int j = 0; j < m; j++) {
                if((i & (1 << j)) != 0) {
                    sum += honey_m[j];
                    value += honey_m[j] * honey_m[j];
                }
            }

            if(sum <= c) // 꿀의 최대 양을 넘지 않을때만
                honey = Math.max(honey, value); // 최대 수익 갱신
        }
        return honey;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            data = new int[n][n];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    data[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            honey_m = new int[m]; // 채취할 수 있는 꿀 m개

            System.out.println("#" + tc + " " + honey_collection()); // 최대 수익 출력
        }
    }
}

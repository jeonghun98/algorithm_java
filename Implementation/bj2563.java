package algorithm_java.Implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 2563 색종이
public class bj2563 {
    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()); //색종이 수
        boolean data[][] = new boolean[100][100];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for(int j = x; j < x+10; j++) {	//x,y ~ x+10, y+10을 색칠하기
                for(int k = y; k < y+10; k++) {
                    data[j-1][k-1] = true;
                }
            }
        }

        int result = 0;
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(data[i][j]) result += 1; //색칠한 곳이라면 넓이 +1
            }
        }
        System.out.println(result);
    }
}

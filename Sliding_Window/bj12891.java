package algorithm_java.Sliding_Window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 12891 DNA 비밀번호
public class bj12891 {
    static int n, m;
    static char dna[];
    static int include[];
    static char acgt[];

    public static boolean pw_check() {	//비밀번호 최소 문자나왔는 확인
        for(int j = 0; j < 4; j++) {	//A,C,G,T 포함 문자열이 있다면 감소시켰음 -> 0보다 크다면 해당 문자열이 최소 이상 등장 x -> false
            if(include[j] > 0)
                return false;
        }
        return true;
    }
    public static int pw() { //슬라이딩으로 pw 확인
        int result = 0;
        for(int i = 0; i < m; i++) {	//처음부터 m만큼 pw 확인
            for(int j = 0; j < 4; j++) {//A,C,G,T 순서대로 확인
                if(dna[i] == acgt[j])
                    include[j]--;
            }
        }

        if(pw_check()) result++;		//만약 pw 최소조건이 된다면 result증가

        for(int i = m; i < n; i++) {	//바뀌는 값들만 작업
            int j = i - m;
            for(int k = 0; k < 4; k++) {
                if(dna[j] == acgt[k])
                    include[k]++;		//전 값 다시 더해주고
            }
            for(int k = 0; k < 4; k++) {
                if(dna[i] == acgt[k])	//새로운 값들은 빼준다
                    include[k]--;
            }
            if(pw_check()) result++;	//만약 pw 최소조건이 된다면 result증가
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dna = br.readLine().toCharArray();
        include = new int [4];
        acgt = new char []{'A', 'C', 'G', 'T'};

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            include[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(pw());
    }
}



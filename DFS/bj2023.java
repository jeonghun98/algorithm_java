package algorithm_java.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// Baekjoon Online Judge 2023 신기한 소수
public class bj2023 {
    static StringBuilder sb = new StringBuilder();
    static int n;

    public static boolean isPrime(int n) { //소수 판별
        if(n < 2)
            return false;
        for(int i = 2; i*i <= n; i++) {
            if(n%i == 0)
                return false;
        }
        return true;
    }

    public static void amazing_prime() {
        int pow_n = (int) Math.pow(10, n);	// 10^n
        int pow_n_1 = (int) Math.pow(10, (n-1)); // 10^(n-1)

        for(int i = pow_n_1 ; i < pow_n;) {		//해당 숫자의 왼쪽부터 소수인지 확인
            boolean check = true;				//소수라면 true;
            for(int k = pow_n_1 ; k >= 1 ; k /= 10) {
                if(!isPrime((int)i/k)) {		//소수가 아니라면 false
                    check = false;
                    i += k;						//왼쪽자리부터 확인하므로 해당 크기만큼 i증가 -> 시간 초과 방지
                    break;
                }
            }
            if(check) {	//소수라면
                sb.append(i + "\n"); //출력
                i++;				// 소수였다면 +1만 증가
            }
        }
    }

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        amazing_prime();
        System.out.println(sb.toString());
    }
}


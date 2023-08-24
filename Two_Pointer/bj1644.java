package algorithm_java.Two_Pointer;

import java.io.*;
// Baekjoon Online Judge 1644 소수의 연속합
public class bj1644 {
    static int n;
    static boolean isPrime[];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        System.out.println(primeNumber());

    }
    public static int primeNumber() {
        isPrime = new boolean[n+1];
        isPrime[0] = isPrime[1] = true;
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(!isPrime[i]) {
                for(int j = i*i; j <= n; j+= i) {
                    isPrime[j] = true;
                }
            }
        }

        int start = 2, end = 2;
        int sumPrime = 0;
        int cnt = 0;
        while(end <= n+1) {
            if(sumPrime < n) {
                sumPrime += end++;
                while(end <= n && isPrime[end])
                    end++;
            } else {
                if(sumPrime == n) cnt++;
                sumPrime -= start++;
                while(start <= n && isPrime[start])
                    start++;
            }
        }
        return cnt;
    }
}

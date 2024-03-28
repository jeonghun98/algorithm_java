package algorithm_java.Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Baekjoon Online Judge 28709 와일드카드 괄호 문자열
public class bj28709 {
    public static boolean check(String data) {
        int cnt = 0;
        int len = data.length();
        int open, close;

        if(data.contains("*")) { // ..*,*....*,*.. -> 가운데는 항상 만족
            for(int i = 0; data.charAt(i)!='*'; i++) {
                if(data.charAt(i) == ')') cnt--;    // => ( + ? 두개의 합한 값보다 크면 올바른 괄호 문자열 x
                else cnt++;                         // (, ? => 어차피 ) 문자가 부족하면 *채우면 됨
                if(cnt < 0) return false;
            }
            cnt = 0;
            for(int i = len-1; data.charAt(i)!='*'; i--) {
                if(data.charAt(i) == '(') cnt--;    // => ) + ? 두개의 합한 값보다 크면 올바른 괄호 문자열 x
                else cnt++;                         // ), ? => 어차피 ( 문자가 부족하면 * 채우면 됨
                if(cnt < 0) return false;
            }
            return true;
        }

        if(len % 2 != 0) return false;
        open = count(data, 0, len, '(');
        close = count(data, 0, len, ')');
        if(open > len/2 || close > len/2) return false;

        for(int i = 0; i < len; i++) {
            char c = data.charAt(i);
            if(c == '?') {
                if(open < len / 2) { // ( 개수가 반보다 작으면 ? 으로 ( 바꿈
                    c = '(';
                    open++;
                } else {
                    c = ')';
                }
            }
            if(c == '(') {
                cnt++;
            } else {
                cnt--;
                if(cnt < 0) return false;
            }
        }
        return true;
    }
    public static int count(String data, int start, int end, char find) {
        int cnt = 0;
        for(int i = start; i < end; i++) {
            if(data.charAt(i) == find) cnt++;
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= t; tc++) {
            sb.append((check(br.readLine()) ? "YES" : "NO") + "\n");
        }
        System.out.print(sb);
    }
}

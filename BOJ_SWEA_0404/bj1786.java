package algorithm_java.BOJ_SWEA_0404;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class bj1786 { // 찾기 KMP
    static int cnt;
    static ArrayList<Integer> list;

    public static int[] makeTable(char[] pattern) {
        int n = pattern.length;
        int table[] = new int[n];

        int idx = 0;
        for(int i = 1; i < n; i++) {
            while(idx > 0 && pattern[i] != pattern[idx]) {
                idx = table[idx-1];
            }
            if(pattern[i] == pattern[idx]) {
                idx += 1;
                table[i] = idx;
            }
        }
//		System.out.println(Arrays.toString(table));
        return table;
    }

    public static void KMP(char[] text, char[] pattern) {
        int table[] = makeTable(pattern);

        int n1 = text.length;
        int n2 = pattern.length;

        int idx = 0;
        for(int i = 0; i < n1; i++) {
            while(idx > 0 && text[i] != pattern[idx]) {
                idx = table[idx-1];
            }
            if(text[i] == pattern[idx]) {
                if(idx == n2-1) {
                    list.add(i-idx+1);
                    cnt += 1;

                    idx = table[idx];
                }else {
                    idx += 1;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = null;

        char T[] = br.readLine().toCharArray();
        char P[] = br.readLine().toCharArray();

        cnt = 0;
        list = new ArrayList<>();

        KMP(T,P);
        System.out.println(cnt);
        for(Integer li : list) {
            System.out.print(li + " ");
        }
    }
}

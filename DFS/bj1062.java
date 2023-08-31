package algorithm_java.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 1062 가르침
public class bj1062 {

    static int n, k;
    static boolean[] alpha_check;
    static String[] word_list;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        word_list = new String[n];

        for(int i = 0; i < n; i++) {
            String temp = br.readLine();
            word_list[i] = temp.substring(4, temp.length()-4); // 앞뒤 기본 단어 자르기
        }

        if(k < 5) {		//5개 이하라면 기본 문자도 x -> 0
            System.out.println(0);
            return;
        }
        if(k == 26) {	//26개의 단어라면 n단어 모두 읽을 수 있음
            System.out.println(n);
            return;
        }

        alpha_check = new boolean[26];
        char alpha[] = {'a','c','i','t','n'};
        for(int i = 0; i < alpha.length; i++)
            alpha_check[alpha[i]-'a'] = true; //a,c,i,t,n -> true

        find(0, 0);
        System.out.println(result);
    }

    public static void find(int cnt, int start) {
        if(cnt == k - 5) {					//기본 단어 제외한 조합
            int count = 0;
            for(int i = 0; i < n; i++) {	//모든 단어 확인
                boolean perfect_word = true;//가르칠 수 있는 단어인지 확인
                for(int j = 0; j < word_list[i].length(); j++) {	//앞뒤 제거한 문자 확인
                    if(!alpha_check[word_list[i].charAt(j) - 'a']) {//해당 문자가 선택되어있지 않았다면 break
                        perfect_word = false;
                        break;
                    }
                }
                if(perfect_word) count++;	//현재 조합으로 가르칠 수 있는 단어라면 count++
            }
            result = Math.max(result, count); //현재 조합으로 읽을 수 있는 단어의 개수와 result와 비교해서 저장
            return;
        }

        for(int i = start; i < 26; i++) { // 조합
            if(alpha_check[i] == false) { // a,c,i,n,t를 제외한 문자로 k-5개 선택
                alpha_check[i] = true;
                find(cnt + 1, i);		// 재귀함수(조합)
                alpha_check[i] = false;
            }
        }
    }
}


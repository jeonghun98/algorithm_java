package algorithm_java.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea5215 {
    static int n, l;	//재료의 수, 제한 칼로리
    static int data[][];	//맛에 대한 점수와 칼로리 넣을 배열
    static int max_score;	//제일 높은 선호 점수

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            max_score = 0;
            data = new int[n][2];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                data[i][0] = Integer.parseInt(st.nextToken());
                data[i][1] = Integer.parseInt(st.nextToken());
            }
            hamburger(0,0,0);
            sb.append("#" + tc +  " " + max_score + "\n");
        }
        System.out.print(sb.toString());
    }

    public static void hamburger(int start, int score, int cal) {
        if(cal > l) return;	//제한 칼로리 넘어가면 return

        if(start > 0) {		// 1개 이상 선택되었다면 선호점수 갱신
            max_score = Math.max(max_score, score);
            if(start == n) return;	//n개 선택되면 return
        }
        for(int i = start; i < n; i++) {	//조합
            hamburger(i+1, score + data[i][0], cal + data[i][1]);
        }
    }
}


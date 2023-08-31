package algorithm_java.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea6808 {
    static int card[];
    static int win, lose;
    static boolean isSelected[];

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            card = new int[9];				// 규영이의 카드
            isSelected = new boolean[19]; //1-18개의 카드 체크
            win = 0; lose = 0;			//이기는 경우 지는 경우

            for(int i = 0; i < 9; i++) {
                card[i] = Integer.parseInt(st.nextToken());
                isSelected[card[i]] = true;	//규영이가 낸 카드 체크
            }
            round(0,0);
            sb.append("#" + tc +  " " + win + " " + lose + "\n");
        }
        System.out.println(sb.toString());

    }

    public static void round(int cnt, int result) {
        if(cnt == 9) {				//9장의 카드를 뽑았다면
            if(result > 0) win++;	// 0이상이면 규영이가 이김
            else if (result < 0) lose++;	// 0이하면 규영이가 짐
            return;
        }
        for(int i = 1; i <= 18; i++) {
            if(isSelected[i]) continue;	//사용한 카드면 continue

            isSelected[i] = true;	//사용하지 않은 카드라면 true로 하고
            if(i < card[cnt])
                round(cnt+1, result + (i + card[cnt]));	// 규영이가 이기면 +
            else if(i > card[cnt])
                round(cnt+1, result - (i + card[cnt]));	// 규영이가 지면 -
            isSelected[i] = false;
        }
    }

}

package algorithm_java.BOJ_SWEA_0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj17281 {

	static int n;
	static int baseball[][];
	static int hitters[];
	static int Max_grade;
	
	public static int game() {
		int grade = 0;
		int index = 0;
		int hitter = 0;
		
		for(int inning = 0; inning < n; inning++) { // N이닝 게임
			boolean first_base = false;
			boolean	second_base = false;
			boolean third_base = false;
			int strikeout = 0;
			
			while(true) {
				hitter = hitters[index];
				int result = baseball[inning][hitter];
				switch(result) {
				 case 1 :{ // 안타
					 if(third_base) grade++;
					 
					 third_base = second_base ? true : false;
					 second_base = first_base ? true : false;
					 first_base = true;
					 
					 break;
				 }
				 case 2 : { // 2루타
					 if(third_base) grade++;
					 if(second_base) grade++;
					 
					 third_base = first_base ? true : false;
					 second_base = true;
					 first_base = false;
					 
					 break;
				 }
				 case 3 : { // 3루타
					 if(third_base) grade++;
					 if(second_base) grade++;
					 if(first_base) grade++;
					 
					 first_base = second_base = false;
					 third_base = true;
					 
					 break;
				 }
				 case 4 : { // 홈런
					 if(third_base) grade++;
					 if(second_base) grade++;
					 if(first_base) grade++;
					 
					 grade++;
					 first_base = second_base = third_base = false;
					 break;
				 }
				 case 0 : { // 아웃
					 strikeout++;
					 break;
				 }
				} // switch end
				
				index = (index + 1) % 9;
				if(strikeout == 3) break;
			} 
		}
		return grade;
	}
	
	public static void permu(int cnt, int flag) { // 선수들 순열
		if(cnt == 9) {
			Max_grade = Math.max(Max_grade, game());
			return;
		}
		for(int i = 1; i < 9; i++) {
			if((flag & (1<<i)) != 0) continue;
			if(cnt==3) ++cnt;
			hitters[cnt] = i;
			permu(cnt+1, flag | (1<<i));
		}
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		hitters = new int[9];
		hitters[3] = 0;
		
		baseball = new int[n][9];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				baseball[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Max_grade = 0;
		permu(0,0);
		System.out.println(Max_grade);
		
	}

}

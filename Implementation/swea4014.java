package algorithm_java.Implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea4014 {
	static int n;
	static int x;
	static int data[][];
	public static int length(int x, int y, boolean horizon) { // 현재 위치에서 높이가 동일한 구간까지 길이 반환
		int cnt = 0;
		
		if(horizon) {
			for(int i = y; i < n; i++) {
				if(data[x][y] == data[x][i]) cnt++;
				else return cnt;
			}
		}
		
		else {
			for(int i = x; i < n; i++) {
				if(data[x][y] == data[i][y]) cnt++;
				else return cnt;
			}
		}
		return cnt;
	}
	
	public static int landingStrip(boolean horizon) {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			int w;
			boolean success = true;
			
			for(int j = 0; j < n;) {
				if(horizon) w = length(i,j,horizon);
				else w = length(j,i,horizon);
				
				if(j+w >= n) break;
				
				if((horizon && data[i][j]-1 == data[i][j+w]) || (!horizon && data[j][i]-1 == data[j+w][i])) { // -1 Down
					int tmp_w = w;
					
					if(horizon) w = length(i,j+w,horizon);
					else w = length(j+w,i,horizon);
					
					if(w < x) {success = false; break;}
					else if(w == x) {j += tmp_w + x - 1;}
					else {j += tmp_w + x;}
				}
				
				else if((horizon && data[i][j]+1 == data[i][j+w]) || (!horizon && data[j][i]+1 == data[j+w][i]) ) { // +1 Up
					if(w < x) {success = false; break;}
					else {j += w;}
				}
				else {success = false; break;}
				
			} if(success) cnt++;
		}
		return cnt;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			
			data = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < n; j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.printf("#%d %d\n", tc, landingStrip(true) + landingStrip(false));
			
		}
	}

}

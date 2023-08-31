package algorithm_java.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// Baekjoon Online Judge 1541 잃어버린 괄호
public class bj1541 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String minus[] = line.split("-"); // - 로 분리하기
		
		int result = 0;
		boolean first = true;
		
		for(String m : minus) {
			String plus[] = m.split("\\+"); // + 로 분리하기
			
			int plus_tmp = 0;
			
			for(String p : plus)	// + 분리한 수 다 더하기
				plus_tmp += Integer.parseInt(p);
			
			if(first) { // 처음 숫자라면 +
				first = false;
				result += plus_tmp;
			}
			else result -= plus_tmp; // 처음 숫자가 아니라면 다 -
		}
		System.out.println(result);
	}
}

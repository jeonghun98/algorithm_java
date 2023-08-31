package algorithm_java.Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// Baekjoon Online Judge 10799 쇠막대기
public class bj10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char data[] = br.readLine().toCharArray();

        int result = 0, stick = 0;
        for(int i = 0; i < data.length; i++) {
            if(data[i] == '(') // 쇠막대기 + 1
                stick++;
            else {
                if(data[i-1] == '(') // 레이저라면 --stick 후에 왼쪽 조각들 + result
                    result += --stick;
                else
                {				//쇠막대기가 끝났다면 스틱 감소하고 해당 막대기 자투리 + 1
                    stick--;
                    result++;
                }
            }
        }System.out.println(result);
    }
}

package algorithm_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Scanner;

public class bj9012 {
    public static boolean check(char[] data) {
        int count = 0;
        for(int i = 0; i < data.length; i++) {
            if(data[i] == '(') count++;
            else {
                count--;
                if(count < 0) {
                    return false;
                }
            }
        }
        if(count==0) return true;
        else return false;
    }

    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 선언
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            String temp = br.readLine();
            char[] data = temp.toCharArray();
            boolean result = check(data);
            if(result)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
package algorithm_java.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Baekjoon Online Judge 3226 전화 요금
public class bj3226 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int sum = 0;
        for(int i = 0; i < n; i++) {
            String call = br.readLine();
            String start = call.split(" ")[0];
            int time = Integer.parseInt(call.split(" ")[1]);
            int startH = Integer.parseInt(start.split(":")[0]);
            int startM = Integer.parseInt(start.split(":")[1]);

            if(startH >= 7 && startH < 19) {
                if(startM + time > 60 && startH + 1 == 19) {
                    time -= (60 - startM);
                    sum += (60 - startM) * 10;
                    sum += time * 5;
                } else {
                    sum += time * 10;
                }
            } else {
                if (startM + time > 60 && (startH + 1) % 24 == 7) {
                    time -= (60 - startM);
                    sum += (60 - startM) * 5;
                    sum += time * 10;
                } else {
                    sum += time * 5;
                }
            }
        }System.out.println(sum);
    }
}

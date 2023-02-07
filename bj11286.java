package algorithm_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class bj11286 {
    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> list = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) == Math.abs(o2)) return o1 - o2;
            else return Math.abs(o1) - Math.abs(o2);
        });

        Integer input_d;
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < T; tc++) {
            input_d = Integer.parseInt(br.readLine());
            if(input_d != 0) {
                list.offer(input_d);
            }
            else {

                if(list.isEmpty()) {
                    sb.append(0 + "\n");
                }
                else
                    sb.append(list.poll() + "\n");
            }
        }
        System.out.print(sb.toString());
    }
}

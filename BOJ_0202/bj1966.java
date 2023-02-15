package algorithm_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class bj1966 {
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer t = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(t.nextToken());
            int m = Integer.parseInt(t.nextToken());

            LinkedList<Integer[]> data = new LinkedList<>();
            t = new StringTokenizer(br.readLine());
            int w_max = 0;
            for(int i = 0; i < n; i++) {
                int temp = Integer.parseInt(t.nextToken());
                Integer list[] = {temp, i};
                data.offer(list);
                if(w_max < temp) w_max = temp;
            }
            int result = 0;
            while(!data.isEmpty()) {
                Integer[] temp = data.poll();
                if(temp[0] < w_max) data.offer(temp);
                else {
                    result++;
                    if(temp[1] == m) break;

                    w_max = data.get(0)[0]; //max 값 갱신
                    for(Integer i[] : data) if(w_max < i[0]) w_max = i[0];
                    //System.out.println(Arrays.toString(temp));
                }
            }
            System.out.println(result);
        }
    }
}

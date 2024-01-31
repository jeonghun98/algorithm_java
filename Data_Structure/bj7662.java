package algorithm_java.Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

// Baekjoon Online Judge 7662 이중 우선순위 큐 == programmers 42628 이중우선순위큐
public class bj7662 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for(int t = 0; t < tc; t++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> treemap = new TreeMap<>();

            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char order = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if(order == 'I') {
                    treemap.put(num, treemap.getOrDefault(num, 0) + 1);
                } else {
                    if(treemap.size() == 0) continue;
                    int key;
                    if(num == 1) key = treemap.lastKey(); // 최댓값 삭제
                    else key = treemap.firstKey(); // 최솟값 삭제
                    if(treemap.put(key, treemap.get(key)-1) == 1) { // get -> the previous value associated with key
                        treemap.remove(key);
                    }
                }
            }
            if(treemap.size() == 0) sb.append("EMPTY\n");
            else sb.append(treemap.lastKey() + " " + treemap.firstKey() + "\n");
        }
        System.out.print(sb.toString());
    }
}

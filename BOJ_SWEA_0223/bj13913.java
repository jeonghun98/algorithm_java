package algorithm_java.BOJ_SWEA_0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class bj13913 {
    static int n,k;
    static int time[]; //시간 저장
    static int parent[]; //index의 과거 위치 저장
    static Stack<Integer> stack;

    public static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(start);
        time[start] = 0;

        while(!q.isEmpty()) {
            int now = q.poll();

            if(now == k) { // 동생을 찾았다면
                int index = k;
                stack.push(index); // 스택 <- 동생위치, 동생 위치 전, 그 전 위치...
                while(index != n) {
                    stack.push(parent[index]);
                    index = parent[index];
                }
            }

            int next = now + 1; // + 1 움직이기
            // 다음 위치가 범위 이내이고 전에 방문했던 기록보다 시간이 짧다면 => 시간, 위치, q
            if(0 <= next && next <= 100000 && time[next] > time[now] + 1) {
                time[next] = time[now] + 1;
                parent[next] = now;
                q.add(next);
            }

            next = now - 1; // - 1 움직이기
            if(0 <= next && next <= 100000 && time[next] > time[now] + 1) {
                time[next] = time[now] + 1;
                parent[next] = now;
                q.add(next);
            }

            next = now * 2; // * 2 움직이기
            if(0 <= next && next <= 100000 && time[next] > time[now] + 1) {
                time[next] = time[now] + 1;
                parent[next] = now;
                q.add(next);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        stack = new Stack<>();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        // 여기까지 입력

        time = new int[100002];
        parent = new int[100002];
        Arrays.fill(time, Integer.MAX_VALUE);

        bfs(n);

        // 출력
        sb.append(time[k] + "\n");
        while(!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }
        System.out.println(sb.toString());
    }
}

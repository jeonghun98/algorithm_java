package algorithm_java.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 9466 텀 프로젝트
public class bj9466 {
    static int n;
    static int []arr;
    public static int dfs(int cycleIndex, int s, int cnt, int[] visited, int[] student) {
        if(visited[s] == cycleIndex && student[s] != 0) {   // 현재 사이클이라면
            return cnt - student[s] + 1;                    // 사이클의 크기 return
        }
        if(visited[s] == 0) {           // 방문 하지 않았다면
            visited[s] = cycleIndex;    // 현재 사이클로 방문 체크
            student[s] = ++cnt;         // 현재 몇번째 학생인지 저장
            return dfs(cycleIndex, arr[s], cnt, visited, student); // 재귀
        }
        return 0;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n+1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int result = n; // 결과값 저장
            int[] visited = new int[n+1]; // 현재 사이클 index 로 방문 체크
            int[] student = new int[n+1]; // 몇번째 학생인지 저장
            int cycle = 1;                // 현재 사이클 index
            for(int i = 1; i <= n; i++) {
                if (visited[i]!=0) continue;
                visited[i] = cycle; // 현재 사이클 방문 체크
                student[i] = 1;     // 1번째 학생
                result -= dfs(cycle++, arr[i], 1, visited, student); // 사이클 만든 학생 수 빼기
            }
            sb.append(result + "\n");
        }
        System.out.print(sb.toString());
    }
}

package algorithm_java.DFS;

// programmers 43162 네트워크
public class pm43162 {
    public static boolean[] visited;
    public static void dfs(int n, int[][] computers, int i) {
        visited[i] = true;

        for(int j = 0; j < n; j++) {
            if(computers[i][j] == 1 && !visited[j])
                dfs(n, computers, j);
        }
    }
    public static int solution(int n, int[][] computers) {

        int answer = 0;
        visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
            dfs(n, computers, i);
            answer++;
        }

        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution(3, new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(solution(3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }
}

package algorithm_java.BOJ_SWEA_0228;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj17471 {

    static int n;
    static int popul[];
    static ArrayList<Integer> [] list;

    static boolean isSelected[];
    static int cnt0, cnt1;

    static int result;


    public static int bfs(boolean visited[]) { // bfs 메소드
        Queue<Integer> q = new ArrayDeque<Integer>();

        q.add(1); visited[1] = true;

        for(int i = 2; i < n + 1; i++) {
            if(isSelected[1] == isSelected[i]) continue;

            q.add(i); visited[i] = true;
            break;
        }
        // 각 구역의 위치를 q에 넣기(각각 1개씩)

        while(!q.isEmpty()) {
            int now = q.poll();

            if(isSelected[now]) cnt1 += popul[now]; //선택된 곳은 cnt1로 count
            else cnt0 += popul[now];				//선택되지 않은 곳은 cnt2로 count

            for(int i : list[now]) {	// 갈 수 있는 위치 찾기
                if(!visited[i] && isSelected[i] == isSelected[now]) { // 방문하지 않았고 현재 같은 구역이라면
                    q.add(i); visited[i] = true;	// q에 넣고 방문 체크


                }
            }
        }

        for(int i = 1; i < n+1; i++) {	// 만약 다 방문 x == 불가능한 방법
            if(!visited[i]) return Integer.MAX_VALUE;
        }
        return Math.abs(cnt0-cnt1); // 가능한 방법이라면 return 인구 차이
    }


    public static void combination(int cnt,int start, int cnt_max) { // 조합
        if(cnt == cnt_max) {
            cnt0 = 0; cnt1 = 0;
            result = Math.min(bfs(new boolean[n+1]), result); // 1 ~ n/2의 조합을 선정했다면 bfs 메소드 호출
            return;
        }
        for(int i = start; i <= n; i++) {
            isSelected[i] = true;
            combination(cnt+1, i+1, cnt_max);
            isSelected[i] = false;
        }
    }

    public static void baekjoon() { // 1 ~ n/2까지의 조합 메소드 호출
        for(int i = 1; i <= n / 2; i++) {
            isSelected = new boolean[n+1];
            combination(0, 1, i);
        }
    }

    public static void main(String[] args) throws Exception, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());

        popul = new int[n+1];
        list = new ArrayList[n+1];

        for(int i = 0; i < n+1; i++)
            list[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < n+1; i++)
            popul[i] = Integer.parseInt(st.nextToken());

        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for(int j = 0; j < k; j++) {
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 여기까지 입력

        // 결과값 초기화
        result = Integer.MAX_VALUE;
        baekjoon(); // 백준시 두 구역으로 나누기
        System.out.println(result == Integer.MAX_VALUE ? -1 : result); // 못 찾으면 -1
    }
}

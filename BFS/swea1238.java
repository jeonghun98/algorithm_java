package algorithm_java.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea1238 {
    static int data[][];
    static class pos{
        int n, time;

        public pos(int n, int time) { //위치와 시간 -> 클래스
            this.n = n;
            this.time = time;
        }

    }
    public static int bfs(int start) {
        int time = 0;
        int result = 0;

        Queue<pos> q = new ArrayDeque<pos>();
        q.add(new pos(start,0)); //시작 위치 넣어줌

        boolean visited[] = new boolean[101]; // 방문 체크
        visited[start] = true;

        while(!q.isEmpty()) {
            pos now = q.poll(); //현재 값

            if(time == now.time) // 시간이 변하지 않았다면 -> 결과는 연락받은 사람 중에 가장 큰 숫자 저장
                result = Math.max(result, now.n);
            else {
                time++; result = now.n;	// 시간이 변했다면 시간 증감, 결과는 현재 값으로 저장
            }


            for(int i = 1; i < 101; i++) {	// 현재 위치에서 갈 수 있는 위치 확인
                if(data[now.n][i] == 1 && !visited[i]) {	// 방문하지 않았다면

                    visited[i] = true;	//방문체크 하고
                    q.offer(new pos(i,now.time+1));	//q에 넣어주기
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for(int tc = 1; tc <= 10; tc++) {
            data = new int[101][101];

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // n개 입력
            int start = Integer.parseInt(st.nextToken()); //시작 위치

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n/2; i++) { //2개씩 받으므로 n/2만큼 반복
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                data[from][to] = 1; //from -> to를 1로 바꿔주기
            }

            System.out.println("#" + tc + " " + bfs(start));
        }
    }
}

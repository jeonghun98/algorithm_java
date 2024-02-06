package algorithm_java.DFS;
import java.util.*;

// programmers 43164 여행경로
class pm43164 {
    static int len;
    static String[] answer;
    static boolean find;
    public static void dfs(String start, int cnt, int seq[], boolean visited[], String[][] tickets) {
        if(cnt == len) {
            boolean alphaSeq = false;
            if(find) { // 경로가 2이상일 경우 알파벳 순서 비교
                for(int i = 0; i < len; i++) {
                    int tmp = answer[i+1].compareTo(tickets[seq[i]][1]);
                    if(tmp == 0) continue;
                    if(tmp > 0) alphaSeq = true;
                    break;
                }
            }
            if(!find || alphaSeq) { // 처음 가능한 경로이거나 알파벳 순서가 앞선 경로
                for(int i = 0; i < len; i++)
                    answer[i+1] = tickets[seq[i]][1];
                find = true;
            }
            return;
        }
        for(int i = 0; i < len; i++) {
            if(visited[i]) continue;

            if(tickets[i][0].equals(start)) {
                visited[i] = true;
                seq[cnt] = i;
                dfs(tickets[i][1], cnt+1, seq, visited, tickets);
                visited[i] = false;
            }
        }
    }
    public static String[] solution(String[][] tickets) {
        len = tickets.length;

        answer = new String[len+1];
        answer[0] = "ICN";
        find = false;

        int seq[] = new int[len];
        boolean visited[] = new boolean[len];

        for(int i = 0; i < len; i++) {
            if(tickets[i][0].equals("ICN")) {
                visited[i] = true;
                seq[0] = i;
                dfs(tickets[i][1],1, seq, visited, tickets);
                visited[i] = false;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        // ["ICN", "JFK", "HND", "IAD"]
        System.out.println((solution(new String[][] {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}})));
        // ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
        System.out.println((solution(new String[][] {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}})));
    }
}
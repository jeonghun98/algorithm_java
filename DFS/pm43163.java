package algorithm_java.DFS;
import java.util.*;

// programmers 43163 단어 변환
class pm43163 {
    static int answer;
    public static int compare(char[] w1, char[] w2) {
        int cnt = 0;
        for(int i = 0; i < w1.length; i++) {
            if(w1[i] != w2[i]) cnt++;
            if(cnt > 1) return cnt;
        }
        return cnt;
    }
    public static void dfs(int idx, int cnt, boolean visited[],
                    char[] begin, char[] target, char[][] words) {

        if(cnt > words.length) return;

        if(cnt > 0) {
            if(compare(words[idx], target) == 0) {
                answer = Math.min(answer, cnt);
                return;
            }
        }

        for(int i = 0; i < words.length; i++) {
            if(visited[i]) continue;

            int diff = compare(cnt == 0 ? begin : words[idx], words[i]);
            if(diff > 1) continue;

            visited[i] = true;
            dfs(i, cnt+1, visited, begin, target, words);
            visited[i] = false;
        }
    }
    public static int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;

        char[] b = begin.toCharArray();
        char[] t = target.toCharArray();
        char[][] w = new char[words.length][];
        for(int i = 0; i < words.length; i++)
            w[i] = words[i].toCharArray();

        dfs(0, 0, new boolean[words.length], b, t, w);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public static void main(String[] args) {
        System.out.println((solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}))); // 4
        System.out.println((solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}))); // 0
    }
}
package algorithm_java.DFS;

// programmers 172927 광물 캐기
public class pm172927 {
    static int totalPick;
    static int answer = Integer.MAX_VALUE;
    public static int getMinerals(int pick, int idx, int[] m) {
        if(pick == 0) return m.length <= idx+4 ? m.length-idx : 5;

        int len = m.length < idx+5 ? m.length : idx+5;
        int sum = 0;

        for(int i = idx; i < len; i++) {
            if(m[i] == 2) sum += 1;
            else if(m[i] == 1) sum += pick == 1 ? 1 : 5;
            else sum += pick == 1 ? 5 : 25;
        }

        return sum;
    }
    public static void dfs(int pickCnt[], int cnt, int sum, int picks[], int[] m) {
        if(cnt == totalPick) {
            answer = sum < answer ? sum : answer;
            return;
        }
        for(int i = 0; i < picks.length; i++) {
            if(picks[i] <= pickCnt[i]) continue;
            pickCnt[i]++;
            dfs(pickCnt, cnt+1, sum + getMinerals(i, cnt*5, m), picks, m);
            pickCnt[i]--;
        }
    }
    public static int[] changeInt(String[] m) {
        int tmp[] = new int[m.length];
        for(int i = 0; i < m.length; i++) {
            if(m[i].equals("iron")) tmp[i] = 1;
            else if(m[i].equals("stone")) tmp[i] = 2;
        }
        return tmp;
    }

    public static int solution(int[] picks, String[] minerals) {
        int pLen = 0, mLen = 0;

        for(int p : picks) pLen += p;
        mLen = minerals.length / 5 + (minerals.length % 5 == 0 ? 0 : 1);
        totalPick = pLen < mLen ? pLen : mLen;

        dfs(new int[3], 0, 0, picks, changeInt(minerals));
        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1,3,2}, new String[] {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"})); // 12
        System.out.println(solution(new int[] {0,1,1}, new String[] {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"})); // 50
    }
}

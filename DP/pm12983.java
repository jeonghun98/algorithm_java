package algorithm_java.DP;

// programmers 12983 단어 퍼즐
public class pm12983 {
    class Solution {
        public static int solution(String[] strs, String t) {
            int tLen = t.length();
            int strsLen = strs.length;
            int[] dp = new int[tLen + 1];

            for (int i = 1; i < tLen + 1; i++) {
                for (int j = 0; j < strsLen; j++) {
                    String word = strs[j];
                    int wordLen = word.length();
                    if (i - wordLen < 0) continue;
                    if (word.equals(t.substring(i - wordLen, i))) { // word.endsWith(t.substring(i - wordLen, i))
                        if (i - wordLen == 0) {
                            dp[i] = 1;
                        }
                        if (dp[i - wordLen] > 0) {
                            dp[i] = dp[i] == 0 ?
                                    dp[i - wordLen] + 1 :
                                    Math.min(dp[i - wordLen] + 1, dp[i]);
                        }
                    }
                }
            }
            return dp[tLen] == 0 ? -1 : dp[tLen];

        }
    }

    public static void main(String[] args) {
        pm12983.Solution.solution(new String[] {"ba","na","n","a"}, "banana"); // 3
        pm12983.Solution.solution(new String[] {"app","ap","p","l","e","ple","pp"}, "apple"); // 2
    }
}

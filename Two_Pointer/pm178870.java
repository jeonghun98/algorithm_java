package algorithm_java.Two_Pointer;

// programmers 178870 연속된 부분 수열의 합
public class pm178870 {
    static class Solution {
        public static int[] solution(int[] sequence, int k) {
            int len = sequence.length;
            int reStart = 0, reEnd = len;

            int start = 0, end = -1, sum = 0;
            while(end < len) {
                if(sum < k) {
                    if(++end < len) sum += sequence[end];
                } else if(sum > k) {
                    sum -= sequence[start++];
                } else {
                    int tmpLen = reEnd - reStart;
                    if(end-start < tmpLen) {
                        reStart = start;
                        reEnd = end;
                    }
                    sum -= sequence[start++];
                }
            }
            return new int[] {reStart, reEnd};
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[] { 1, 2, 3, 4, 5}, 7);
    }
}

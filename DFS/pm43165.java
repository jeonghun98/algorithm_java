package algorithm_java.DFS;

// programmers 43165 타겟 넘버
public class pm43165 {
    public static int dfs(int[] numbers, int target, int idx, int sum) {
        if(idx == numbers.length) {
            if(target == sum) return 1;
            return 0;
        }

        return dfs(numbers, target, idx+1, sum+numbers[idx]) +
                dfs(numbers, target, idx+1, sum-numbers[idx]);

    }
    public static int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1,1,1,1,1}, 3));
        System.out.println(solution(new int[] {4,1,2,1}, 4));
    }
}

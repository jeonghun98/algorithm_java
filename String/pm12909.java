package algorithm_java.String;

// programmers 12909 올바른 괄호
public class pm12909 {
    static boolean solution(String s) {
        int cnt = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                cnt++;
            } else {
                if(cnt == 0) return false;
                cnt--;
            }
        }
        if(cnt != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("()()"));   // T
        System.out.println(solution("(())()")); // T
        System.out.println(solution(")()("));   // F
        System.out.println(solution("(()("));   // F
    }
}

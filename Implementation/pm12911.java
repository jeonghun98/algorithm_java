package algorithm_java.Implementation;

// programmers 12911 다음 큰 숫자
public class pm12911 {
    public static int solution(int n) {
        //  String binary = Integer.toBinaryString(n);
        int cnt = Integer.bitCount(n);
        while(true) {
            if(cnt == Integer.bitCount(++n)) return n;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(78));
        System.out.println(solution(15));
    }
}

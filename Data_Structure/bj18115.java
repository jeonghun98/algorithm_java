package algorithm_java.Data_Structure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
// Baekjoon Online Judge 18115 카드 놓기
public class bj18115 {
    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> li = new LinkedList<Integer>();

        int[] data = new int[n];
        for(int i = 0; i < n; i++)
            data[i] = Integer.parseInt(st.nextToken()); // 카드 기술

        for(int i = n-1, num = 1; i >= 0; i--) {	//카드 기술 뒤에서 앞으로, num 카드 번호
            if(data[i] == 1)
                li.add(0, num++);	//제일 앞에 넣기
            else if(data[i] == 2)
                li.add(1,num++);	//앞에서 두번째에 넣기
            else
                li.add(num++);		//제일 뒤에 넣기
        }
        StringBuilder sb = new StringBuilder();
        for(Integer k : li)
            sb.append(k +" ");
        System.out.println(sb.toString());
    }
}

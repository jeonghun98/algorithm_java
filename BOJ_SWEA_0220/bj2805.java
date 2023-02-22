package algorithm_java.BOJ_SWEA_2020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj2805 {
    static int n,m;
    static int tree[];

    private static long binarySearch() {
        int start = 0, end = tree[n-1], mid = 0;
        long result = 0;

        while(start <= end) {
            mid = (start+end)/2;

            long now_tree = 0;	//현재 가져갈 수 있는 나무
            for(int t : tree) {
                if(t > mid) now_tree += (t - mid);
            }

            if(now_tree >= m) {	// 가져갈 수 있는 나무가 m(원한는 길이)보다 크다면 절단기 높이 올리기
                result = mid;	// 해당 값이 최댓값이 될 수 있으므로 저장
                start = mid + 1;
                if(now_tree == m) break; //같다면 while break
            }
            else
                end = mid-1;	//가져갈 수 있는 나무가 m보다 작다면 절단기 높이 낮추기
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        tree = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++)
            tree[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(tree);

        System.out.println(binarySearch());
    }
}

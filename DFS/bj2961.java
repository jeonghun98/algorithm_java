package algorithm_java.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Baekjoon Online Judge 2961 도영이가 만든 맛있는 음식
public class bj2961 {
    static int n;			//재료의 개수
    //	static boolean isSelected[];
    static int data[][];	//레시피 저장
    static int result = Integer.MAX_VALUE;	//결과 값
    static int sourness = 1, bitterness = 0; //신맛과 쓴맛

    //	재귀 사용 -> 수가 겹치지 않도록 start이용해 다음 index부터 접근
    public static void find_min(int start) {
        for(int i = start; i < n; i++) {
            sourness *= data[i][0];
            bitterness += data[i][1];
            result = Math.min(result, Math.abs(sourness - bitterness)); //현재 요리랑 result 중에 작은 값 찾기
            find_min(i+1);
            sourness /= data[i][0];
            bitterness -= data[i][1];
        }
    }
//	부분 집합으로 푸는 방법 -> 공집합을 확인해야한다는 단점
//	public static void find_min_food() {
//		int multi_f = 1;
//		int sum_f = 0;
//		for(int i = 0; i < n; i++) {
//			if(isSelected[i]) {
//				multi_f *= data[i][0];
//				sum_f += data[i][1];
//			}
//		}
//		result = Math.min(result, Math.abs(multi_f - sum_f));
////		System.out.println(result);
//	}
//	public static void generateSubset(int cnt)
//	{
//		if(cnt == n) {
//			boolean isSelected_check = false;
//			for(int i = 0; i < n; i++) {
//				if(isSelected[i]) isSelected_check = true;
//			}
//
//			if(isSelected_check) find_min_food();
//			return;
//		}
//		isSelected[cnt] = true;
//		generateSubset(cnt+1);
//		isSelected[cnt] = false;
//		generateSubset(cnt+1);
//	}


    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
//		isSelected = new boolean[n];
        data = new int[n][2];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//		generateSubset(0);
        find_min(0);
        System.out.println(result);
    }

}


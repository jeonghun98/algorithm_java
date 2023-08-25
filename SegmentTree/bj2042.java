package algorithm_java.SegmentTree;
import java.util.*;
import java.io.*;

public class bj2042{
    static class segmentTree{
        long tree[];
        int treeSize;
        public segmentTree(int arrSize) {
            int h = (int)Math.ceil(Math.log(arrSize)/Math.log(2));
            this.treeSize = (int) Math.pow(2, h+1) - 1;
            tree = new long[treeSize];
        }
        public long init(long[] nums, int node, int start, int end) {
            if(start == end) {
                return tree[node] = nums[start];
            }
            return tree[node] = init(nums, node*2, start, (start + end)/2)
                    + init(nums, node*2 + 1, (start+end)/2+1, end);
        }
        public void update(int node, int start, int end, int idx, long diff) {
            if(idx < start || end < idx) return;

            tree[node] += diff;

            if(start != end) {
                update(node*2, start, (start+end)/2, idx, diff);
                update(node*2+1, (start+end)/2+1, end, idx, diff);
            }
        }

        public long sum(int node, int start, int end, int left, int right) {
            if(start > right || end < left) return 0;

            if(left <= start && end <= right)
                return tree[node];

            return sum(node*2, start,(start + end)/2, left, right) +
                    sum(node*2+1, (start + end)/2+1,end, left, right);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        segmentTree stree = new segmentTree(n);
        long[] arr = new long[n+1];
        for(int i = 1; i <= n; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        stree.init(arr,1,1,n);

        for(int i = 0; i < m+k; i++){
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if(cmd == 1){
                stree.update(1,1,n,a,b-arr[a]);
                arr[a] = b;
            }else{
                sb.append(stree.sum(1,1,n,a,(int)b) +"\n");
            }
        }
        System.out.print(sb.toString());
    }
}

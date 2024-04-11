package algorithm_java.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Baekjoon Online Judge 5639 이진 검색 트리
public class bj5639 {
    static int[] tree = new int[10001];
    static StringBuilder sb;
    static class Node {
        int val;
        Node left, right;
        Node(int val) {
            this.val = val;
        }
        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        void insert(int n) {
            if(n < this.val) {
                if(this.left == null) this.left = new Node(n);
                else this.left.insert(n);
            }
            else {
                if(this.right == null) this.right = new Node(n);
                else this.right.insert(n);
            }
        }
    }

    public static void postOrder(Node cur) {
        if(cur == null) return;
        postOrder(cur.left);
        postOrder(cur.right);
        sb.append(cur.val + "\n");
    }

    public static void postOrder(int root, int end) {
        if(root > end) {
            return;
        }
        int mid = root+1;
        while(mid <= end && tree[mid] < tree[root]) {
            mid++;
        }
        postOrder(root+1, mid-1);
        postOrder(mid, end);
        sb.append(tree[root] + "\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        Node root = new Node(Integer.parseInt(br.readLine()));
        String s;
        while(true) {
            s = br.readLine();
            if(s == null || s.equals("")) break;
            root.insert(Integer.parseInt(s));
        }
        postOrder(root);

//        String s;
//        int idx = 0;
//        while(true) {
//            s = br.readLine();
//            if(s == null || s.equals("")) break;
//            tree[idx++] = Integer.parseInt(s);
//        }
//        postOrder(0, idx-1);

        System.out.print(sb.toString());
    }
}

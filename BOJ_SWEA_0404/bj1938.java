package algorithm_java.BOJ_SWEA_0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

public class bj1938 {
    static int n;
    static char data[][];
    static boolean visited[][][];

    static class Pos{
        int x, y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Tree {
        Pos pos[];
        boolean h;
        int cnt;
        public Tree(Pos p[], int cnt, boolean h) {
            this.pos = p;
            this.cnt = cnt;
            this.h = h;
        }
    }

    static int dx[] = {-1,1,0,0}; //U, D, L, R
    static int dy[] = {0,0,-1,1};

    public static boolean find_e(Tree tree) {
        for(int i = 0; i < 3; i++){
            if(data[tree.pos[i].x][tree.pos[i].y] != 'E') return false;
        }
        return true;
    }
    public static boolean turn_check(Tree tree) {
        int x = tree.pos[1].x;
        int y = tree.pos[1].y;

        for(int i = x-1; i < x+2; i++) {
            for(int j = y-1; j < y+2; j++){
                if(i < 0 || j < 0 || i >= n || j >= n
                || data[i][j] == '1') return false;
            }
        }return true;
    }

    public static boolean visited_check(Tree tree) {
        if(visited[tree.pos[1].x][tree.pos[1].y][tree.h? 0 : 1]) return false;
        return true;
    }

    public static void visit(Tree tree){
        visited[tree.pos[1].x][tree.pos[1].y][tree.h? 0 : 1] = true;
    }

    public static int bfs(Tree start) {
        Queue<Tree> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty()) {
            Tree t = q.poll();
            if (find_e(t)) {
                return t.cnt;
            }

//            System.out.print((t.h ? "horizon  "  : "vertical  "));
//            for(int i = 0; i< 3; i++)
//                System.out.print(t.pos[i].x + ", " + t.pos[i].y + " / ");
//            System.out.println();
            // T
            if(turn_check(t)) {
                Pos tmp[] = new Pos[3];
                tmp[1] = new Pos(t.pos[1].x, t.pos[1].y);
                tmp[0] = t.h ? new Pos(t.pos[1].x-1, t.pos[1].y) : new Pos(t.pos[1].x, t.pos[1].y-1);
                tmp[2] = t.h ? new Pos(t.pos[1].x+1, t.pos[1].y) : new Pos(t.pos[1].x, t.pos[1].y+1);

                if(visited_check(new Tree(tmp, t.cnt+1, !t.h))) {
//                    System.out.println("turn - nx : " + tmp[1].x + ", ny : " + tmp[1].y);
                    visit(new Tree(tmp,t.cnt+1, !t.h));
                    q.add(new Tree(tmp,t.cnt+1, !t.h));
                }
            }

            //U, D, L, R
            for (int i = 0; i < 4; i++) {
                Pos tmp[] = new Pos[3];
                int check = 0;
                for (int j = 0; j < 3; j++) {
                    int nx = t.pos[j].x + dx[i];
                    int ny = t.pos[j].y + dy[i];
//                    if(t.pos[1].x == 3 && t.pos[1].y == 2) System.out.println("i " + i +" nx : " + nx + ", ny : " + ny);
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n || data[nx][ny] == '1') break;
                    else {
                        check++;
                        tmp[j] = new Pos(nx, ny);
                    }
                }
                if (check == 3 && visited_check(new Tree(tmp,t.cnt+1, t.h))) {
//                    if(t.pos[1].x == 3 && t.pos[1].y == 2) System.out.println("nx : " + tmp[1].x + ", ny : " + tmp[1].y);
                    visit(new Tree(tmp,t.cnt+1, t.h));
                    q.add(new Tree(tmp,t.cnt+1, t.h));
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        data = new char[n][n];
        visited = new boolean[n][n][2]; //가로 세로

        Pos tmp[] = new Pos[3];
        int index = 0;

        for(int i = 0; i < n; i++) {
            data[i] = br.readLine().toCharArray();

            for(int j = 0; j < n; j++){
                if(data[i][j] == 'B') {
                    tmp[index++] = new Pos(i,j);
                }
            }
        }
        boolean horizon = false;
        if(tmp[0].x == tmp[1].x) horizon = true;

        visited[tmp[1].x][tmp[1].y][horizon? 0 : 1] = true;

        System.out.println(bfs(new Tree(tmp,0,horizon)));
    }
}

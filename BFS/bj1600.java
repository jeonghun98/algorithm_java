package algorithm_java.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// Baekjoon Online Judge 1600 말이 되고픈 원숭이
public class bj1600 {
    static int k;
    static int w,h;
    static int data[][];

    static int dx[] = {0,1,0,-1,1,2,2,1,-1,-2,-2,-1};
    static int dy[] = {1,0,-1,0,2,1,-1,-2,-2,-1,1,2};
    static class Pos{
    	int x, y, h, cnt;

		public Pos(int x, int y, int h, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.h = h;
			this.cnt = cnt;
		}
    }

    public static int monkey() {
    	boolean visited[][][] = new boolean[h][w][k+1];
    	Queue<Pos> q = new ArrayDeque<>();
    	q.add(new Pos(0,0,0,0));
    	visited[0][0][0] = true;
    	
    	int result = Integer.MAX_VALUE;
    	while(!q.isEmpty()) {
    		Pos now = q.poll();
    		
    		if(now.x == h-1 && now.y == w-1)
    			return now.cnt;
    		
    		for(int i = 0; i < 12; i++) {
    			int nx = now.x + dx[i];
    			int ny = now.y + dy[i];
    			if(nx < 0 || ny < 0 || nx >= h || ny >= w || data[nx][ny] == 1) continue;
    			
    			if(i < 4) { //원숭이
    				if(!visited[nx][ny][now.h]) {
    					visited[nx][ny][now.h] = true;
        				q.add(new Pos(nx,ny,now.h,now.cnt+1));
    				}
    			}
    			else {	// 말
    				if(now.h < k &&!visited[nx][ny][now.h+1]) {
    					visited[nx][ny][now.h+1] = true;
        				q.add(new Pos(nx,ny,now.h+1,now.cnt+1));
    				}
    			}
    		}
    	}
    	return -1;
    }
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        data = new int[h][w];
        for(int i = 0; i < h; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < w; j++){
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(monkey());
    }
}

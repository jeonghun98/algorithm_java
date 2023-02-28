package algorithm_java.BOJ_SWEA_0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class swea3124 {
    static class Vertex implements Comparable<Vertex>{
        int no;
        int weight;

        public Vertex(int no, int weight) {
            super();
            this.no = no; // num
            this.weight = weight; // 가중치
        }

        @Override
        public int compareTo(Vertex o) {
            return this.weight - o.weight;
        }

    }
    static int V, E;
    static ArrayList<Vertex> [] list;
    static int[] minEdge;
    static boolean[] visited;

    public static long prims() { // 프림 메소드
        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        long result = 0;
        int nodeCount = 0;

        minEdge[1] = 0;
        pq.add(new Vertex(1,0));

        while(!pq.isEmpty()) {
            Vertex minVertex = pq.poll();

            if(visited[minVertex.no]) continue;

            result += minVertex.weight;
            visited[minVertex.no] = true;

            if(++nodeCount == V) break;

            for(Vertex v : list[minVertex.no]) {
                if(!visited[v.no]
                        && minEdge[v.no] > v.weight) {
                    minEdge[v.no] = v.weight;
                    pq.add(new Vertex(v.no, minEdge[v.no]));
                }
            }

        }
        return result;
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            minEdge = new int[V+1];
            visited = new boolean[V+1];

            list = new ArrayList[V+1];
            for(int i = 1; i < V+1; i++) {
                minEdge[i] = Integer.MAX_VALUE;
                list[i] = new ArrayList<>();
            }

            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                list[a].add(new Vertex(b,c));
                list[b].add(new Vertex(a,c));
            }
//        	System.out.println(prims());
            System.out.println("#" + tc + " "+ prims()); // 출력
        }
    }
}
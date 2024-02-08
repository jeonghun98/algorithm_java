package algorithm_java.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Baekjoon Online Judge 14867 물통
public class bj14867 {
    static int maxA, maxB;
    static int findA, findB;
    public static class Beaker {
        int a, b, cnt;
        Beaker(int a, int b, int cnt) {
            this.a = a;
            this.b = b;
            this.cnt = cnt;
        }
        // equals를 재정의한 클래스에는 hashCode도 반드시 재정의한다.

        // hashcode() 재정의 x ->
        // 같은 값 객체라도 해시값이 다를 수 있음. 따라서 HashTable에서 해당 객체가 저장된 버킷을 찾을 수 없다.
        // 특히 HashMap의 key 값으로 해당 객체를 사용할 경우 문제가 발생

        // equals()를 재정의 x ->
        // hashcode()가 만든 해시값을 이용해 객체가 저장된 버킷을 찾을 수는 있지만
        // 해당 객체가 자신과 같은 객체인지 값을 비교할 수 없기 때문에 null을 리턴

        // equals 하지 않으면 인스턴트는 오직 자기 자신하고만 같음
        @Override
        public boolean equals(Object o) {
            Beaker b = (Beaker) o;
            return this.a == b.a && this.b == b.b;
        }
        @Override
        public int hashCode(){
            return (""+a+b).hashCode();
        }
    }
    public static int bfs() {
        Queue<Beaker> q = new ArrayDeque<>();
        HashSet<Beaker> visited = new HashSet<>();

        q.add(new Beaker(0,0,0));
        visited.add(new Beaker(0,0,0));

        while(!q.isEmpty()) {
            Beaker now = q.poll();
            if(now.a == findA && now.b == findB) {
                return now.cnt;
            }
            // FILL A
            if(now.a < maxA) {
                Beaker tmp = new Beaker(maxA, now.b, now.cnt+1);
                if(!visited.contains(tmp)) {
                    q.add(tmp);
                    visited.add(tmp);
                }
            }
            // FILL B
            if(now.b < maxB) {
                Beaker tmp = new Beaker(now.a, maxB, now.cnt+1);
                if(!visited.contains(tmp)) {
                    q.add(tmp);
                    visited.add(tmp);
                }
            }
            // EMPTY A
            if(now.a > 0) {
                Beaker tmp = new Beaker(0, now.b, now.cnt+1);
                if(!visited.contains(tmp)) {
                    q.add(tmp);
                    visited.add(tmp);
                }
            }
            // EMPTY B
            if(now.b > 0) {
                Beaker tmp = new Beaker(now.a, 0, now.cnt+1);
                if(!visited.contains(tmp)) {
                    q.add(tmp);
                    visited.add(tmp);
                }
            }
            // MOVE WATER A -> B
            if(now.a + now.b <= maxB) {
                Beaker tmp = new Beaker(0, now.a + now.b, now.cnt+1);
                if(!visited.contains(tmp)) {
                    q.add(tmp);
                    visited.add(tmp);
                }
            } else {
                Beaker tmp = new Beaker(now.a + now.b - maxB, maxB, now.cnt+1);
                if(!visited.contains(tmp)) {
                    q.add(tmp);
                    visited.add(tmp);
                }
            }
            // MOVE WATER B -> A
            if(now.a + now.b <= maxA) {
                Beaker tmp = new Beaker(now.a + now.b, 0, now.cnt+1);
                if(!visited.contains(tmp)) {
                    q.add(tmp);
                    visited.add(tmp);
                }
            } else {
                Beaker tmp = new Beaker(maxA, now.a + now.b - maxA, now.cnt+1);
                if(!visited.contains(tmp)) {
                    q.add(tmp);
                    visited.add(tmp);
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxA = Integer.parseInt(st.nextToken());
        maxB = Integer.parseInt(st.nextToken());
        findA = Integer.parseInt(st.nextToken());
        findB = Integer.parseInt(st.nextToken());
        System.out.println(bfs());
    }
}

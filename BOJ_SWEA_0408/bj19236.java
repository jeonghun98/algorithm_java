package algorithm_java.BOJ_SWEA_0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj19236 {
    //↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int dx[] = {-1,-1,0,1,1,1,0,-1};
    static int dy[] = {0,-1,-1,-1,0,1,1,1};
    static int result;
    public static class Pos{
        int x, y, dir, eat;
        public Pos(int x, int y, int dir, int eat) {
            this.x = x;
            this.y = y;;
            this.dir = dir;
            this.eat = eat;
        }
        public Pos(int x, int y, int dir) {
            this(x,y, dir,0);
        }
    }
    public static void fishMove(Pos idData[], int idArr[][]) {
        int id = -1;
        for(Pos fish : idData) {
            id++;
            if(fish == null) continue;

            for(int i = 0; i < 8; i++) {
                int nx = fish.x + dx[(fish.dir + i) % 8];
                int ny = fish.y + dy[(fish.dir + i) % 8];
                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || idArr[nx][ny] == 0) continue;

                // ID 위치 swap
                int tmpId = idArr[nx][ny];
                idArr[nx][ny] = idArr[fish.x][fish.y];
                idArr[fish.x][fish.y] = tmpId;


                //위치 swap - idData(fish)
                if(tmpId != -1) {
                    idData[tmpId].x = fish.x;
                    idData[tmpId].y = fish.y;
                }

                fish.x = nx;
                fish.y = ny;
                fish.dir = (fish.dir + i) % 8;

                break;
            }
        }
    }

    public static int[][] copyArr(int arr[][]) {
        int tmp[][] = new int[4][4];
        for(int i = 0; i < 4; i++) tmp[i] = Arrays.copyOf(arr[i], 4);
        return tmp;
    }

    public static Pos[] copyClass(Pos arr[]) {
        Pos tmp[] = new Pos[17];
        for(int i = 0; i < 17; i++) {
            if(arr[i] == null) continue;
            tmp[i] = new Pos(arr[i].x, arr[i].y, arr[i].dir);
        }
        return tmp;
    }

    public static int RemainId(Pos idData[]) {
        int num = 0;
        for(int i = 0; i < 17; i++) {
            if(idData[i] == null) continue;
            num += i;
        }
        return num;
    }
    public static void sharkEat(Pos shark, Pos idData[], int idArr[][]){ // 물고기 먹기

        if(RemainId(idData) + shark.eat < result) return;

        fishMove(idData, idArr);

        int nx = shark.x;
        int ny = shark.y;
        for(int i = 1; i < 4; i++) {
            nx += dx[shark.dir];
            ny += dy[shark.dir];
            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) break; // 더 이상 움직일 수 없다면

            if(idArr[nx][ny] == -1) continue; // 물고기가 없다면

            // shark copy // (int x, int y, int dir, int eat)
            Pos tmpShark = new Pos(nx, ny, idData[idArr[nx][ny]].dir , shark.eat + idArr[nx][ny]);

            // idData(fish) arr copy
            Pos tmpIdData[] = copyClass(idData);
            tmpIdData[idArr[nx][ny]] = null; // 먹은 물고기 삭제

            // arr(id) copy
            int[][] tmpId = copyArr(idArr);
            tmpId[nx][ny] = 0; // 상어 자리 -> 0
            tmpId[shark.x][shark.y] = -1; // 상어가 먹은 물고리 자리 -> -1

            sharkEat(tmpShark, tmpIdData, tmpId);
        }
        result = Math.max(result, shark.eat);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int idArr[][] = new int[4][4];
        Pos idData[] = new Pos[17];
        Pos shark = null;

        for(int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                int id = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                idArr[i][j] = id;

                if(i == 0 && j == 0) shark = new Pos(0,0,dir, id);
                else idData[id] = new Pos(i,j,dir);
            }
        }
        idArr[0][0] = 0;
        sharkEat(shark,idData, idArr);
        System.out.println(result);
    }
}
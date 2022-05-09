//https://www.acmicpc.net/problem/16985
//Solved : 22/04/26

import java.util.*;
import java.io.*;

class Main{
    static int[] dr={1,0,0,-1,0,0}, dc={0,1,0,0,-1,0}, dz={0,0,1,0,0,-1};
    static int[][][] map;
    static int rst = Integer.MAX_VALUE;
    static boolean[][][] chk;
    static boolean[] mapchk;
    static List<int[][]> list;
    static int[] arr = new int[]{0,0,0,0,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        map = new int[5][5][5];
        mapchk = new boolean[5];
        list = new ArrayList<>();
        for(int i=0; i<5; i++){
            int[][] tmp = new int[5][5];
            for(int j=0; j<5; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<5; k++){
                    tmp[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            list.add(tmp);
        }

        calc(0);

        bw.write(Integer.toString(rst!=Integer.MAX_VALUE ? rst : -1));
        bw.close();
        br.close();
    }
    static void calc(int idx){
        if(idx==5){
            perm(0);
            return;
        }
        for(int i=0; i<5; i++){
            if(mapchk[i]) continue;
            map[idx] = list.get(i);
            mapchk[i] = true;
            arr[idx] = i;
            calc(idx+1);
            mapchk[i] = false;
        }
    }
    static void perm(int idx){
        if(idx==5){
            int dist = BFS();
            rst = rst < dist ? rst : dist;
            return;
        }
        for(int i=0; i<4; i++){
            perm(idx+1);
            rotate(idx);
        }
    }
    static int BFS(){
        if(map[0][0][0]!=1||map[4][4][4]!=1) return Integer.MAX_VALUE;

        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{0,0,0,0});
        chk = new boolean[5][5][5];
        chk[0][0][0] = true;
        int nr, nc, nz;
        while (!que.isEmpty()){
            int[] now = que.poll();
            for(int i=0; i<6; i++){
                nr = now[0] + dr[i];
                nc = now[1] + dc[i];
                nz = now[2] + dz[i];
                if(nr==4 && nc==4 &&nz==4) return now[3]+1;
                if(isValid(nr, nc, nz) && !chk[nr][nc][nz] && map[nr][nc][nz]==1){
                    que.add(new int[]{nr, nc, nz, now[3]+1});
                    chk[nr][nc][nz] = true;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static void rotate(int idx){
        int[][] tmp = new int[5][5];
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++)
                tmp[i][j] = map[idx][5-j-1][i];
        }
        map[idx] = tmp;
    }

    static boolean isValid(int nr, int nc, int nz){
        return nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && nz >= 0 && nz < 5;
    }
}
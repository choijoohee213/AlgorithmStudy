//https://www.acmicpc.net/problem/17143
//Solved : 22/03/30

import java.util.*;
import java.io.*;

class Main{
    static int R,C;
    static int[][] map;
    static Shark[] sharks;
    static int[] dr = {-1,1,0,0}, dc = {0,0,1,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        sharks = new Shark[M+1];
        int rst = 0;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            map[x][y] = i+1;
            sharks[i+1] = new Shark(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<C; i++){
            for(int j=0; j<R; j++){
                if(map[j][i]!=0){
                    rst += sharks[map[j][i]].size;
                    map[j][i] = 0;
                    break;
                }
            }
            move();
        }

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
    static void move(){
        int[][] next = new int[R][C];
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] != 0){
                    Shark now = sharks[map[i][j]];
                    int nR = i + now.spd*dr[now.dist];
                    int nC = j + now.spd*dc[now.dist];
                    while (nR < 0 || nR >= R || nC < 0 || nC >= C) {
                        if (nR < 0) {
                            nR = -1 * nR;
                            now.dist = now.dist == 0 ? 1 : 0;
                        } else if (nR > R - 1) {
                            nR = R - 1 - (nR - R + 1);
                            now.dist = now.dist == 0 ? 1 : 0;
                        } else if (nC < 0) {
                            nC = -1 * nC;
                            now.dist = now.dist == 2 ? 3 : 2;
                        } else if (nC > C - 1) {
                            nC = C - 1 - (nC - C + 1);
                            now.dist = now.dist == 2 ? 3 : 2;
                        }
                    }
                    if(next[nR][nC]==0) {
                        next[nR][nC] = map[i][j];
                        sharks[map[i][j]] = now;
                    }else{
                        if(sharks[next[nR][nC]].size < now.size){
                            next[nR][nC] = map[i][j];
                            sharks[map[i][j]] = now;
                        }
                    }
                }
            }
        }
        map = next;
    }
}

class Shark{
    int spd, dist, size;

    public Shark(int spd, int dist, int size) {
        this.spd = spd;
        this.dist = dist;
        this.size = size;
    }
}

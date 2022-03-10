//https://www.acmicpc.net/problem/16954
//Solved : 22/03/09

import java.util.*;
import java.io.*;

class Main{
    static char[][] map = new char[8][8];
    static int[] dr = {0,-1,-1,0,1,1,1,0,-1}, dc = {0,0,1,1,1,0,-1,-1,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0; i<8; i++) map[i] = br.readLine().toCharArray();

        bw.write(Integer.toString(BFS()));
        bw.close();
        br.close();
    }

    static int BFS(){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[8][8][8];
        q.add(new int[]{7,0,0});
        while(!q.isEmpty()){
            int[] n = q.poll();
            //System.out.println(Arrays.toString(n));
            if(n[2]==8) return 1;
            for(int i=0; i<9; i++){
                int nr = n[0]+dr[i];
                int nc = n[1]+dc[i];
                if(nr<0||nr>=8||nc<0||nc>=8||visited[n[2]][nr][nc]) continue;

                int line = nr-n[2]-1;
                if(line<-1) return 1;
                else if(line<0){
                    if(map[line+1][nc]=='#') continue;
                    visited[n[2]][nr][nc] = true;
                    q.add(new int[]{nr,nc,n[2]+1});
                }else {
                    if (map[line + 1][nc] == '#' || map[line][nc] == '#') continue;
                    visited[n[2]][nr][nc] = true;
                    q.add(new int[]{nr, nc, n[2] + 1});
                }
            }
        }
        return 0;
    }
}
/*
fail
######..
.#.#.###
........
........
........
........
........
........

ok
######..
.#######
######..
........
........
........
........
........

fail
########
........
........
........
........
........
........
........

 */
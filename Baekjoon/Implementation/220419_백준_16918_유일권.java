//https://www.acmicpc.net/problem/16918
//Solved : 22/04/19

import java.util.*;
import java.io.*;

class Main_16918{
    static int[] dr={-1,0,1,0}, dc={0,1,0,-1};
    static int[][] map;
    static int R,C,N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str;
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for(int i=0; i<R; i++){
            str = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = str.charAt(j)-'O';
            }
        }
        int time=1;
        while(time<=N){
            if(time%2==0)  fill(time);
            boom(time);
            time++;
        }

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                sb.append(map[i][j]<0?'.':'O');
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    static void fill(int time){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] < 0){
                    map[i][j] = time;
                }
            }
        }
    }
    static void boom(int time){
        if(time<2) return;
        Queue<int[]> que = new ArrayDeque<>();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == time-3) que.add(new int[]{i,j});
            }
        }
        while (!que.isEmpty()){
            int[] now = que.poll();
            map[now[0]][now[1]] = -1;
            for(int i=0; i<4; i++){
                int nr = now[0]+dr[i];
                int nc = now[1]+dc[i];
                if(nr<0||nc<0||nr>=R||nc>=C) continue;
                map[nr][nc] = -1;
            }
        }
    }
}
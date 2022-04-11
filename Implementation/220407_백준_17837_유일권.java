//https://www.acmicpc.net/problem/17837
//Solved : 22/04/08

import java.util.*;
import java.io.*;

class Main{
    static class Chess{
        int r,c,dir,idx = 1;

        public Chess(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
        public void changeDir(){
            if(dir==0) dir = 1;
            else if(dir==1) dir=0;
            else if(dir==2) dir=3;
            else if(dir==3) dir=2;
        }
    }

    static int[] dr = {0, 0, -1, 1}, dc = {1, -1, 0, 0};
    static int[][] map;
    static Chess[] chess;
    static int N, K;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        chess = new Chess[K];
        boolean flag = false;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            chess[i] = new Chess(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        }
        int rst = 1;
        loop:while (rst<8){
            for(int i=0; i<K; i++){
                int nr = chess[i].r + dr[chess[i].dir];
                int nc = chess[i].c + dc[chess[i].dir];

                if(nr<0||nr>=N||nc<0||nc>=N||map[nr][nc]==2) blue(i, chess[i].r, chess[i].c);
                else if(map[nr][nc]==1) red(i, nr, nc);
                else white(i, nr, nc);

                if(fin()){
                    flag = true;
                    break loop;
                }
            }

            rst++;
        }
        if(!flag) rst = -1;
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static int chk(int r, int c){
        int time = 1;
        for(int i=0; i<K; i++){
            if(chess[i].r==r&&chess[i].c==c) time++;
        }
        return time;
    }

    static boolean fin(){
        for(int i=0; i<K; i++){
            if(chess[i].idx==4) return true;
        }
        return false;
    }

    static void white(int now, int nr, int nc){
        int idx = chk(nr, nc);
        for(int i=0; i<K; i++){
            if(chess[i].r==chess[now].r && chess[i].c==chess[now].c && chess[i].idx > chess[now].idx){
                chess[i].r = nr;
                chess[i].c = nc;
                chess[i].idx = chess[i].idx - chess[now].idx + idx;
            }
        }
        chess[now].r = nr;
        chess[now].c = nc;
        chess[now].idx = idx;
    }

    static void red(int now, int nr, int nc){
        int idx = chk(nr, nc);
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> y[1]-x[1]);
        for(int i=0; i<K; i++){
            if(chess[i].r==chess[now].r && chess[i].c==chess[now].c && chess[i].idx > chess[now].idx){
                pq.add(new int[]{i, chess[i].idx});
            }
        }
        int t = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            chess[cur[0]].r = nr;
            chess[cur[0]].c = nc;
            chess[cur[0]].idx = idx+t;
            t++;
        }
        chess[now].r = nr;
        chess[now].c = nc;
        chess[now].idx = idx+t;
    }

    static void blue(int now, int nr, int nc){
        chess[now].changeDir();
        nr = nr + dr[chess[now].dir];
        nc = nc + dc[chess[now].dir];
        if(nr<0||nr>=N||nc<0||nc>=N||map[nr][nc]==2) return;
        else if(map[nr][nc]==0) white(now, nr, nc);
        else if(map[nr][nc]==1) red(now, nr, nc);
    }
}
//https://www.acmicpc.net/problem/17822
//Solved : 22/04/04

import java.util.*;
import java.io.*;

class Main{
    static int N, M, rst = 0;
    static int[][] circle;
    static int[] dr = {0,1,0,-1}, dc = {1,0,-1,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        circle = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                circle[i][j] = Integer.parseInt(st.nextToken());
                rst += circle[i][j];
            }
        }

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            round(x, d, t);
            boolean flag = false;
            for(int r=0; r<N; r++){
                for(int c=0; c<M; c++){
                    if(circle[r][c]!=0&&chk(r,c)){
                        change(r,c);
                        flag = true;
                    }
                }
            }
            if(!flag){
                pm();
            }
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static void round(int x, int d, int t){
        if(d==0) t=M-t;
        for(int i = x-1; i<N; i+=x){
            int[] next = new int[M];
            for(int j=0; j<M; j++) {
                if (j + t < M) next[j] = circle[i][j+t];
                else next[j] = circle[i][j+t-M];
            }
            circle[i] = next;
        }
    }

    static void change(int r, int c){
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{r, c});
        int now = circle[r][c], nr, nc;
        int[] t;
        while (!que.isEmpty()){
            t = que.poll();
            if(circle[t[0]][t[1]]==0) continue;
            circle[t[0]][t[1]] = 0;
            rst -= now;
            for(int i=0; i<4; i++){
                nr = t[0] + dr[i];
                nc = t[1] + dc[i];
                if(nr<0||nr>=N) continue;
                else if(nc==M) nc=0;
                else if(nc<0) nc=M-1;
                if(circle[nr][nc]!=now) continue;
                que.add(new int[]{nr, nc});
            }
        }
    }
    static boolean chk(int r, int c){
        for(int i=0; i<2; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nc==M) nc=0;
            else if(nc==-1) nc=M-1;
            if(nr>=N) continue;
            if (circle[r][c] == circle[nr][nc]) return true;
        }
        return false;
    }
    static void pm(){
        int t = 0;
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(circle[r][c]>0) t++;
            }
        }
        double aver = 0;
        if(t>0) {
            aver = (double) rst / t;
        }
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(circle[r][c]!=0){
                    if(circle[r][c] > aver){
                        circle[r][c]--;
                        rst--;
                    }
                    else if(circle[r][c] < aver){
                        circle[r][c]++;
                        rst++;
                    }
                }
            }
        }
    }
}
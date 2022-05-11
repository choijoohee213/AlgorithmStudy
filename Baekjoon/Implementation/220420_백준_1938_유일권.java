//https://www.acmicpc.net/problem/1938
//Solved : 22/04/20

import java.util.*;
import java.io.*;

class Main{
    static int[] start, fin, dr={-1,1,0,0,-1,1,1,-1}, dc={0,0,-1,1,1,1,-1,-1};      //상,하,좌,우, 대각선 순서
    static char[][] map;
    static boolean[][][] chk;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        chk = new boolean[N][N][2];
        for(int i=0; i<N; i++) map[i] = br.readLine().toCharArray();

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j]=='B'){
                    int n1 = i+dr[0];
                    int n2 = i+dr[1];
                    if(isIn(n1, n2) && map[n1][j]=='B' && map[n2][j]=='B'){
                        start = new int[]{i,j,0};
                    }
                    n1 = j+dc[2];
                    n2 = j+dc[3];
                    if(isIn(n1, n2) && map[i][n1]=='B' && map[i][n2]=='B'){
                        start = new int[]{i,j,2};
                    }
                }
                else if(map[i][j]=='E'){
                    int n1 = i+dr[0];
                    int n2 = i+dr[1];
                    if(isIn(n1, n2) && map[n1][j]=='E' && map[n2][j]=='E'){
                        fin = new int[]{i,j,0};
                    }
                    n1 = j+dc[2];
                    n2 = j+dc[3];
                    if(isIn(n1, n2) && map[i][n1]=='E' && map[i][n2]=='E'){
                        fin = new int[]{i,j,2};
                    }
                }

            }
        }
        bw.write(Integer.toString(BFS()));
        bw.close();
        br.close();
    }
    static int BFS(){
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{start[0],start[1],start[2],0});
        chk[start[0]][start[1]][start[2]/2] = true;
        while (!que.isEmpty()){
            int[] now =  que.poll();
            for(int i=0; i<4; i++){
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if(isIn(nr,nc) && isIn(nr+dr[now[2]], nc+dc[now[2]]) && isIn(nr+dr[now[2]+1], nc+dc[now[2]+1])
                        && !chk[nr][nc][now[2]/2]
                        && map[nr][nc]!='1'
                        && map[nr+dr[now[2]]][nc+dc[now[2]]]!='1'
                        && map[nr+dr[now[2]+1]][nc+dc[now[2]+1]]!='1'){
                    if(nr==fin[0] && nc==fin[1] && now[2]==fin[2]) return now[3]+1;
                    que.add(new int[]{nr, nc, now[2], now[3]+1});
                    chk[nr][nc][now[2]/2] = true;
                }
            }
            if(chk[now[0]][now[1]][((now[2]+2)%4)/2]){
                continue;
            }
            boolean flag = true;
            for(int i=0; i<8; i++){
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if(!isIn(nr,nc) || map[nr][nc]=='1'){
                    flag = false;
                    break;
                }
            }
            if(flag){
                if(now[0]==fin[0] && now[1]==fin[1] && (now[2]+2)%4==fin[2]) return now[3]+1;
                que.add(new int[]{now[0], now[1], (now[2]+2)%4, now[3]+1});
                chk[now[0]][now[1]][((now[2]+2)%4)/2] = true;
            }
        }
        return 0;
    }
    static boolean isIn(int x, int y){
        return !(x<0||x>=N||y<0||y>=N);
    }
}
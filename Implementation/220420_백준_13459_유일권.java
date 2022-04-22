//https://www.acmicpc.net/problem/13459
//Solved : 22/04/19

import java.util.*;
import java.io.*;

class Main_13459{
    static class MT{
        int time;
        int rx, ry, bx, by;

        public MT(int time, int rx, int ry, int bx, int by) {
            this.time = time;
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
        }
    }

    static char[][] map;
    //static boolean[][][][] visited;
    static int N, M, rst=-1;
    static int[] R = new int[2];
    static int[] B =  new int[2];
    static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        //visited = new boolean[N][M][N][M];

        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                if(map[i][j] == 'R'){
                    R[0] = i;
                    R[1] = j;
                }else if(map[i][j] == 'B'){
                    B[0] = i;
                    B[1] = j;
                }
            }
        }

        BFS();

        bw.write(Integer.toString(rst==-1?0:1));
        bw.close();
        br.close();
    }
    static void BFS(){
        Queue<MT> q = new ArrayDeque<>();
        q.add(new MT(0, R[0], R[1], B[0], B[1]));
        //visited[R[0]][R[1]][B[0]][B[1]] = true;
        loop:while(!q.isEmpty()){
            MT now = q.poll();
            if(now.time>=10){
                rst=-1;
                break;
            }
            for(int i=0; i<4; i++){
                MT next = move(now, i);
                if(next.bx==-1) continue;
                if(next.rx==-1){
                    rst = next.time;
                    break loop;
                }
                q.add(next);
            }
        }
    }
    static MT move(MT m, int d){
        MT mt = new MT(m.time+1, m.rx, m.ry, m.bx, m.by);
        for(int t=0; t<2; t++){
            while(true){
                if(mt.rx==-1||map[mt.rx+dr[d]][mt.ry+dc[d]]=='O'){
                    mt.rx = -1;
                    mt.ry = -1;
                    break;
                }
                if(map[mt.rx+dr[d]][mt.ry+dc[d]]=='#' ||
                        (mt.rx+dr[d]==mt.bx)&&(mt.ry+dc[d]==mt.by))  break;
                mt.rx += dr[d];
                mt.ry += dc[d];

            }
            while(true){
                if(mt.bx==-1||map[mt.bx+dr[d]][mt.by+dc[d]]=='O'){
                    mt.bx = -1;
                    mt.by = -1;
                    break;
                }
                if(map[mt.bx+dr[d]][mt.by+dc[d]]=='#' ||
                        (mt.rx==mt.bx+dr[d])&&(mt.ry==mt.by+dc[d]))  break;
                mt.bx += dr[d];
                mt.by += dc[d];
            }
        }
        return mt;
    }
}
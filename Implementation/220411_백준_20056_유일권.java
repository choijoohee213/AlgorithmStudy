//https://www.acmicpc.net/problem/20056
//Solved :

import java.util.*;
import java.io.*;

class Main{
    static class Ball{
        int r, c, m, dir, s;
        //위치(r,c), 무게, 방향 속도
        boolean moved = true;
        public Ball(int r, int c, int m, int s, int dir) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.dir = dir;
            this.s = s;
        }
        public Ball(int r, int c, int m, int s, int dir, boolean moved) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.dir = dir;
            this.s = s;
            this.moved = moved;
        }
    }

    static int[] dr={-1,-1,0,1,1,1,0,-1}, dc={0,1,1,1,0,-1,-1,-1};
    static int[][] dirs = {{0,2,4,6},{1,3,5,7}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int rst=0;

        List<Ball>[][] map = new List[N][N];
        for(int i=0; i<N; i++) for(int j=0; j<N; j++) map[i][j] = new ArrayList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            map[r][c].add(new Ball(r, c, w, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            rst += w;
        }
        boolean turn = false;
        for(int t=0; t<K; t++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j].isEmpty()) continue;

                    for(int k=0; k<map[i][j].size(); k++){
                        Ball tmp = map[i][j].get(k);
                        if(turn==tmp.moved) continue;
                        int nr = tmp.r + dr[tmp.dir]*(tmp.s%N);
                        int nc = tmp.c + dc[tmp.dir]*(tmp.s%N);

                        if(nr > 0) nr %= N;
                        else if(nr < 0) nr = N - Math.abs(nr);
                        if(nc > 0) nc %= N;
                        else if(nc < 0) nc = N - Math.abs(nc);

                        tmp.r = nr;
                        tmp.c = nc;
                        tmp.moved = !tmp.moved;
                        map[nr][nc].add(tmp);
                        map[i][j].remove(k--);
                    }
                }
            }
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j].size()>1){
                        int totalW=0;
                        int totalS=0;
                        int time=0;
                        boolean[] isOk = new boolean[]{true, true};  //0 : 짝, 1 : 홀
                        for(int z=map[i][j].size()-1; z>=0; z--) {
                            Ball tmp = map[i][j].get(z);
                            time++;
                            totalW += tmp.m;
                            totalS += tmp.s;
                            if(isOk[0]) isOk[0] = tmp.dir%2 == 0;
                            if(isOk[1]) isOk[1] = tmp.dir%2 == 1;
                            map[i][j].remove(z);
                        }
                        int idx = 0;
                        int weight = totalW/5;
                        int speed = totalS/time;
                        rst = rst - (totalW-weight*4);
                        if(weight==0) continue;
                        if(!isOk[0]&&!isOk[1]) idx=1;
                        for(int z=0; z<4; z++){
                            map[i][j].add(new Ball(i,j,weight,speed,dirs[idx][z], turn));
                        }
                    }
                }
            }
            turn = !turn;
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}
/*
4 6 4
1 1 5 1 1
3 3 5 1 5
1 3 5 1 3
3 1 5 1 7
2 2 5 1 3
3 2 5 1 2

4 9 5
3 2 8 5 2
3 3 19 3 4
3 1 7 1 1
4 4 6 4 0
2 1 6 2 5
4 3 9 4 3
2 2 16 1 2
4 2 17 5 3
3 4 3 5 7

 */
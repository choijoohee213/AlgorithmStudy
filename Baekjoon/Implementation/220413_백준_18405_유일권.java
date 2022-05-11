//https://www.acmicpc.net/problem/18405
//Solved : 22/04/13

import java.util.*;
import java.io.*;

class Main{
    static int[][] map;
    static int N, K;
    static int[] dr = {-1,0,1,0}, dc={0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> x[3]==y[3] ? x[2]-y[2] : x[3]-y[3]);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]!=0){
                    pq.add(new int[]{i, j, map[i][j], 0});
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken())-1;
        int Y = Integer.parseInt(st.nextToken())-1;
        while (!pq.isEmpty()){
            int[] now = pq.poll();
            if(now[3]>=S) break;
            for(int i=0; i<4; i++){
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if(nr<0||nr>=N||nc<0||nc>=N||map[nr][nc]!=0) continue;
                map[nr][nc] = now[2];
                pq.add(new int[]{nr, nc, now[2], now[3]+1});
            }
        }
        bw.write(Integer.toString(map[X][Y]));
        bw.close();
        br.close();
    }
}
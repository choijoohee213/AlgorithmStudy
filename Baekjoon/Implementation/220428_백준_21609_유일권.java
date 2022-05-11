//https://www.acmicpc.net/problem/21609
//Solved : 21/05/09

import java.util.*;
import java.io.*;

class Main_21609{
    static int[][] map;
    static int N, M, rst;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rst = 0;
        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        autoPlay();

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static void autoPlay(){
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> x[0]!=y[0] ? y[0]-x[0] : x[1]!=y[1] ? y[1]-x[1] : x[2]!=y[2] ? y[2]-x[2] : y[3]-x[3]);
        while (true){
            pq.clear();
            visited = new boolean[N][N];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j]>0 && !visited[i][j]){
                        int[] tmp = BFS(i, j, false);
                        pq.add(new int[]{tmp[0], tmp[1], i, j});
                    }
                }
            }
            if(pq.isEmpty()) break;
            int[] now = pq.poll();
            if(now[0]==1) break;
            visited = new boolean[N][N];
            BFS(now[2], now[3], true);
            rst += (now[0] * now[0]);
            fall();
            rotate();
            fall();
        }
    }

    static int[] BFS(int r, int c, boolean change){
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{r,c});
        visited[r][c] = true;
        int size = 1;
        int zeros = 0;
        int color = map[r][c];
        if(change) map[r][c] = -2;
        while (!que.isEmpty()){
            int[] now = que.poll();
            for(int i=0; i<4; i++){
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;
                if(map[nr][nc]==0 || map[nr][nc]==color){
                    que.add(new int[]{nr, nc});
                    size++;
                    if(map[nr][nc]==0) zeros++;
                    if(change) map[nr][nc] = -2;
                    visited[nr][nc] = true;
                }
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j]==0) {
                    visited[i][j] = false;
                }
            }
        }
        return new int[]{size, zeros};
    }

    static void fall(){
        for(int i=N-2; i>=0; i--){
            for(int j=0; j<N; j++) {
                if (map[i][j] >= 0){
                    int nr = i+1;
                    while(nr<N){
                        if(map[nr][j] != -2) break;
                        nr++;
                    }
                    nr--;
                    map[nr][j] = map[i][j];
                    if(nr!=i) map[i][j] = -2;
                }
            }
        }
    }
    static void rotate(){
        int[][] tmp = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                tmp[i][j] = map[j][N-i-1];
            }
        }
        map = tmp;
    }
    static void print(String str){
        System.out.println(str);
        for(int[] i : map){
            System.out.println(Arrays.toString(i));
        }
    }
}
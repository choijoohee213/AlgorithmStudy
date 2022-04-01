//https://www.acmicpc.net/problem/17406
//Solved : 22/03/31

import java.util.*;
import java.io.*;

class Main{
    static int N,M,K, rst=Integer.MAX_VALUE;
    static int[][] arr, move;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static boolean[] chk;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        move = new int[K][3];
        chk = new boolean[K];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken())-1;
            move[i][1] = Integer.parseInt(st.nextToken())-1;
            move[i][2] = Integer.parseInt(st.nextToken());
        }
        dfs(0);

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
    static void dfs(int idx){
        if(idx == K){
            //for(int[] i:arr) System.out.println(Arrays.toString(i));
            int score = Integer.MAX_VALUE;
            for(int i=0; i<N; i++){
                int now = 0;
                for(int j=0; j<M; j++){
                    now += arr[i][j];
                }
                score = score < now ? score : now;
            }
            rst = rst < score ? rst : score;
            return;
        }
        for(int i=0; i<K; i++){
            if(chk[i]) continue;
            chk[i] = true;
            int[][] tmp = new int[N][M];
            for(int j=0; j<N; j++) tmp[j] = arr[j].clone();

            round(move[i][0], move[i][1], move[i][2]);
            dfs(idx+1);

            arr = tmp;
            chk[i] = false;
        }
    }
    static void round(int r, int c, int s){
        for (int j = 0; j < s; j++) {
            int x = r-s + j, y = c-s + j;
            int value = arr[x][y];
            int idx = 0;
            while (idx < 4) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];

                if (nx >= r-s + j && ny >= c-s + j && nx <= r + s - j && ny <= c + s - j) {
                    arr[x][y] = arr[nx][ny];
                    x = nx;
                    y = ny;
                } else idx++;
            }
            arr[r-s + j][c-s + j+1] = value;
        }
    }
}
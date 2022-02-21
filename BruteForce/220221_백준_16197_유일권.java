package beakjoon.d220222.q16197;
//https://www.acmicpc.net/problem/16197
//Solved : 22/02/22

import java.util.*;
import java.io.*;

public class Main {
    static int rst = Integer.MAX_VALUE;
    static int[][] coin = new int[2][2];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        int coins = 0;
        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                if(map[i][j]=='o'){
                    coin[coins][0] = i;
                    coin[coins][1] = j;
                    coins++;
                }
            }
        }

        dfs(map,0);
        if(rst >=11) rst = -1;

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static void dfs(char[][] map, int idx){
        if(idx==11) return;                         //10이하만 확인
        for(int i=0; i<4; i++){
            if(move_out(map, i)==1){
                rst = rst < idx+1 ? rst : idx+1;
                return;
            }else if(move_out(map, i)==-1) continue;     //둘다 나가면 다시

            boolean[] moved = {true,true};
            for(int j=0; j<2; j++) {
                int nextX = coin[j][0] + dx[i];
                int nextY = coin[j][1] + dy[i];
                if (map[nextX][nextY] != '#') { //벽이 아니라 이동 가능하면
                    coin[j][0] += dx[i];
                    coin[j][1] += dy[i];
                } else moved[j] = false;        //이동을 못한 경우
            }

            dfs(map,idx+1);

            for(int j=0; j<2; j++) {            //이동했음 다시 원상태
                if (moved[j]) {
                    coin[j][0] -= dx[i];
                    coin[j][1] -= dy[i];
                }
            }
        }
    }
    static int move_out(char[][] map, int i){             //너무 지저분....
        int N = map.length;
        int M = map[0].length;
        boolean coin1_out = coin[0][0]+dx[i]>=N||coin[0][0]+dx[i]<0||coin[0][1]+dy[i]>=M||coin[0][1]+dy[i]<0;
        boolean coin2_out = coin[1][0]+dx[i]>=N||coin[1][0]+dx[i]<0||coin[1][1]+dy[i]>=M||coin[1][1]+dy[i]<0;

        if(coin1_out&&(!coin2_out)){ //1번 만 나간경우
            return 1;
        }
        if((!coin1_out)&&coin2_out){ //2번 만 나간경우
            return 1;
        }
        if(coin1_out&&coin2_out){//둘다 나간 경우
            return -1;
        }
        return 0;
    }
}

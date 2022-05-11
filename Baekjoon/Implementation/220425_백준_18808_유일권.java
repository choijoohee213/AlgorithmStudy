//https://www.acmicpc.net/problem/
//Solved :

import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static int r, c;
    static int rst;
    static boolean[][] map;
    static int[][] sticker;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];

        int K = Integer.parseInt(st.nextToken());
        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            sticker = new int[r][c];
            for(int i = 0; i < r; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < c; j++)
                    sticker[i][j] = Integer.parseInt(st.nextToken());
            }
            setPosition(0);
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
    static boolean isPossible(int y, int x){
        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                // 이미 스티커가 있다면 불가능
                if(sticker[i][j] == 1 && map[y + i][x + j])
                    return false;
            }
        }
        //붙일 수 있다면 바로 붙임
        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                if(sticker[i][j] == 1){
                    rst++; // 면적 추가
                    map[y + i][x + j] = true;
                }
            }
        }
        return true;
    }

    static void rotate(){
        //시계방향
        int[][] tmp = new int[c][r];
        for(int i = 0; i<c; i++){
            for(int j = 0; j<r; j++)
                tmp[i][j] = sticker[r-j-1][i];
        }
        //회전하였기 때문에 r과 c는 변경
        r = tmp.length;
        c = tmp[0].length;
        sticker = tmp;
    }

    static void setPosition(int dir){
        //네 번 회전을 한다면 원상태이기 때문에 return 처리한다.
        if(dir == 4) return;

        for(int i = 0; i <= N-r; i++){
            for(int j = 0; j <= M-c; j++){
                if(isPossible(i, j))
                    return;
            }
        }
        rotate();               //회전하고 다시 반복
        setPosition(dir + 1);
    }
}
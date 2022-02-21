package beakjoon.d220221.q14500;
//https://www.acmicpc.net/problem/14500
//Solved : 22/02/21

import java.util.*;
import java.io.*;

public class Main {
    static int rst = 0;
    static int[][] d = {{-1,0},{0,1},{1,0},{0,-1}};
    static int[][] map;
    static boolean[][] chk;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];                    //맵
        chk = new boolean[N][M];                //사용 여부
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(N,M,i, j, 0, 0);
                int tmp = chk_U(N, M, i, j);
                rst = rst > tmp ? rst : tmp;        //칸마다 확인하며 최대 값 저장
            }
        }

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }

    static void dfs(int N, int M, int r, int c, int idx, int score){
        if(idx == 4){
            rst = rst > score ? rst : score;
            return;
        }
        for(int i=0; i<4; i++){
            if(r+d[i][0]<0||r+d[i][0]>=N||c+d[i][1]<0||c+d[i][1]>=M||chk[r+d[i][0]][c+d[i][1]]) continue;   //배열 범위 벗어나면 취소
            chk[r][c] = true;
            dfs(N, M,r+d[i][0], c+d[i][1], idx+1, score + map[r][c]);
            chk[r][c] = false;
        }
    }

    static int chk_U(int N, int M, int r, int c){
        int sum = 0, tmp = 0;
        if(c+2<M){  //우측 ㅜ,ㅗ
            if(r+1< N) tmp = map[r][c] + map[r][c+1] + map[r+1][c+1] + map[r][c+2];
            sum = sum > tmp ? sum : tmp;
            if(r-1>=0) tmp = map[r][c] + map[r][c+1] + map[r-1][c+1] + map[r][c+2];
            sum = sum > tmp ? sum : tmp;
        }
        if(r+2< N) {   //하단 ㅓㅏ
            if (c + 1 < M) tmp = map[r][c] + map[r + 1][c] + map[r + 1][c + 1] + map[r + 2][c];
            sum = sum > tmp ? sum : tmp;
            if (c - 1 >= 0) tmp = map[r][c] + map[r + 1][c] + map[r + 1][c-1] + map[r + 2][c];
            sum = sum > tmp ? sum : tmp;
        }
        return sum;
    }
}


/*
public class Main {
    static int rst = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                chk(map, i, j);
            }
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
    static void chk(int[][] map, int r, int c){
        int[] arr= new int[3];
        arr[0] = chk_1(map, r, c);
        arr[1] = chk_2(map, r, c);
        arr[2] = chk_3(map, r, c);
        System.out.print(r+" "+c+" ");
        for(int i=0; i<3; i++){
            rst = rst > arr[i] ? rst : arr[i];
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    static int chk_1(int[][] map, int r, int c){                //1자 형태 모형
        int sum1 = 0, sum2 = 0;
        if(r+3<map.length){
            for(int i=0; i<4; i++) sum1 += map[r+i][c];
        }
        if(c+3<map[0].length){
            for(int i=0; i<4; i++) sum2 += map[r][c+i];
        }
        return sum1 > sum2 ? sum1 : sum2;
    }
    static int chk_2(int[][] map, int r, int c){                //네모 모양
        int sum = 0;
        if(r+1 < map.length&&c+1 < map[0].length){
            sum = map[r][c]+map[r+1][c]+map[r][c+1]+map[r+1][c+1];
        }
        return sum;
    }
    static int chk_3(int[][] map, int r, int c){
        int dr = 0, dc = 0;
        if(c+2<map[0].length){
            int tmp = 0;
            if(r+1< map.length){
                tmp = tmp > map[r][c+2] + map[r+1][c+2] ? tmp : map[r][c+2] + map[r+1][c+2];
                tmp = tmp > map[r][c+2] + map[r+1][c] ? tmp : map[r][c+2] + map[r+1][c];
                tmp = tmp > map[r+1][c+1] + map[r+1][c+2] ? tmp : map[r+1][c+1] + map[r+1][c+2];
                tmp = tmp > map[r+1][c+1] +map[r][c+2] ? tmp : map[r+1][c+1] +map[r][c+2];
            }
            if(r-1>=0) {
                tmp = tmp > map[r][c+2] + map[r-1][c+2] ? tmp : map[r][c+2] + map[r-1][c+2];
                tmp = tmp > map[r][c+2] + map[r-1][c] ? tmp : map[r][c+2] + map[r-1][c];
                tmp = tmp > map[r-1][c+1] + map[r-1][c+2] ? tmp : map[r-1][c+1] + map[r-1][c+2];
                tmp = tmp > map[r-1][c+1] +map[r][c+2] ? tmp : map[r-1][c+1] +map[r][c+2];
            }
            dc = map[r][c] + map[r][c+1] + tmp;
        }
        if(r+2< map.length) {
            int tmp = 0;
            if (c + 1 < map[0].length){
                tmp = tmp > map[r + 2][c] + map[r + 2][c + 1] ? tmp : map[r + 2][c] + map[r + 2][c + 1];
                tmp = tmp > map[r + 2][c] + map[r][c + 1] ? tmp : map[r + 2][c] + map[r][c + 1];
                tmp = tmp > map[r + 2][c + 1] + map[r + 2][c + 1] ? tmp : map[r + 2][c + 1] + map[r + 2][c + 1];
                tmp = tmp > map[r + 2][c] + map[r + 1][c + 1] ? tmp : map[r + 2][c] + map[r + 1][c + 1];
            }
            if (c - 1 >= 0){
                tmp = tmp > map[r + 2][c] + map[r + 2][c - 1] ? tmp : map[r + 2][c] + map[r + 2][c - 1];
                tmp = tmp > map[r + 2][c] + map[r + 2][c - 1] ? tmp : map[r + 2][c] + map[r + 2][c - 1];
                tmp = tmp > map[r + 2][c - 1] + map[r + 1][c - 1] ? tmp : map[r + 2][c - 1] + map[r + 1][c - 1];
                tmp = tmp > map[r + 2][c - 1] + map[r + 2][c] ? tmp : map[r + 2][c - 1] + map[r + 2][c];
            }
            dr = map[r][c] + map[r + 1][c] + tmp;
        }
        return dr > dc ? dr : dc;
    }
}
*/
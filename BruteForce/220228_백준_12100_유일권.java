package beakjoon.d220227.q12100;
//https://www.acmicpc.net/problem/12100
//Solved : 22/02/28

import java.util.*;
import java.io.*;

public class Main {
    static int N, rst = Integer.MIN_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;  j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0);

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
    static void recur(int idx){
        if(idx==5){
            int best = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    best = best > map[i][j] ? best : map[i][j];
                }
            }
            rst = rst > best ? rst : best;
            return;
        }

        int[][] copy = new int[N][N];
        for(int i=0; i<N; i++) copy[i] = map[i].clone();                //배열 복사

        for(int i=0; i<4; i++){
            move(i);
            recur(idx+1);
            for(int j=0; j<N; j++) map[j] = copy[j].clone();            //복사 한곳에서 가져오기
        }
    }
    static void move(int d){
        switch (d){
            case 0:                             //위로 이동
                for(int i=0; i<N; i++){
                    int index = 0;              //값을 넣을 인덱스값
                    int block = 0;              //가장 최근 움직인 블록의 값
                    for(int j=0; j<N; j++){
                        if(map[j][i] != 0){
                            if(block == map[j][i]){
                                map[index-1][i] = block*2;
                                block=0;
                                map[j][i]=0;
                            }
                            else{
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            case 1:
                for(int i=0; i<N; i++){
                    int index = N-1;
                    int block = 0;
                    for(int j=N-1; j>=0; j--){
                        if(map[i][j] != 0){
                            if(block == map[i][j]){
                                map[i][index+1] = block*2;
                                block=0;
                                map[i][j]=0;
                            }
                            else{
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
            case 2:
                for(int i=0; i<N; i++){
                    int index = N-1;
                    int block = 0;
                    for(int j=N-1; j>=0; j--){
                        if(map[j][i] != 0){
                            if(block == map[j][i]){
                                map[index+1][i] = block*2;
                                block=0;
                                map[j][i]=0;
                            }
                            else{
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int i=0; i<N; i++){
                    int index = 0;              //값을 넣을 인덱스값
                    int block = 0;              //가장 최근 움직인 블록의 값
                    for(int j=0; j<N; j++){
                        if(map[i][j] != 0){
                            if(block == map[i][j]){
                                map[i][index-1] = block*2;
                                block=0;
                                map[i][j]=0;
                            }
                            else{
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
        }
    }
}

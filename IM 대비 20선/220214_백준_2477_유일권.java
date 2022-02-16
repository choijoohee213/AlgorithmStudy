package beakjoon.before.q2477;
//https://www.acmicpc.net/problem/2477
//Solved : 22/02/07

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] field = new int[7][2];
        int[] big_sq = new int[2];      //큰 사각형         0가로 1세로
        int[] sm_sq = new int[2];       //작은 사각형
        for(int i=0; i<6; i++){
            st = new StringTokenizer(br.readLine());
            field[i][0] = Integer.parseInt(st.nextToken());     //방향
            field[i][1] = Integer.parseInt(st.nextToken());     //길이
            if(field[i][0]==1||field[i][0]==2){
                big_sq[0] = Math.max(big_sq[0], field[i][1]);   //가로세로 가장 긴변 고르기
            }else{
                big_sq[1] = Math.max(big_sq[1], field[i][1]);
            }
        }
        for(int i=0; i<6; i++){
            if(i<4){
                if(field[i][0]==field[i+2][0]){
                    if(field[i][0]==1){
                        sm_sq[1] = field[i+1][1];
                        if(field[i+1][0]==3){
                            sm_sq[0] = field[i][1];
                        }else{
                            sm_sq[0] = field[i+2][1];
                        }
                    }else if(field[i][0]==2){
                        sm_sq[1] = field[i+1][1];
                        if(field[i+1][0]==3){
                            sm_sq[0] = field[i+2][1];
                        }else{
                            sm_sq[0] = field[i][1];
                        }
                    }else if(field[i][0]==3){
                        sm_sq[0] = field[i+1][1];
                        if(field[i+1][0]==1){
                            sm_sq[1] = field[i+2][1];
                        }else{
                            sm_sq[1] = field[i][1];
                        }
                    }else{
                        sm_sq[0] = field[i+1][1];
                        if(field[i+1][0]==1){
                            sm_sq[1] = field[i][1];
                        }else{
                            sm_sq[1] = field[i+2][1];
                        }
                    }
                    break;
                }
            } else{
                int a, b;
                if(i==4){
                    a = 1;
                    b = 4;
                }else{
                    a = -5;
                    b = 6;
                }
                if(field[i][0]==field[b-i][0]){
                    if(field[i][0]==1){
                        sm_sq[1] = field[i+a][1];
                        if(field[i+a][0]==3){
                            sm_sq[0] = field[i][1];
                        }else{
                            sm_sq[0] = field[b-i][1];
                        }
                    }else if(field[i][0]==2){
                        sm_sq[1] = field[i+a][1];
                        if(field[i+a][0]==3){
                            sm_sq[0] = field[b-i][1];
                        }else{
                            sm_sq[0] = field[i][1];
                        }
                    }else if(field[i][0]==3){
                        sm_sq[0] = field[i+a][1];
                        if(field[i+a][0]==1){
                            sm_sq[1] = field[b-i][1];
                        }else{
                            sm_sq[1] = field[i][1];
                        }
                    }else{
                        sm_sq[0] = field[i+a][1];
                        if(field[i+a][0]==1){
                            sm_sq[1] = field[i][1];
                        }else{
                            sm_sq[1] = field[b-i][1];
                        }
                    }
                    break;
                }
            }
        }
        int rst = K * (big_sq[0]*big_sq[1] - sm_sq[0]*sm_sq[1]);
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}

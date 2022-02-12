package beakjoon.before.q2116;
//https://www.acmicpc.net/problem/2116
//22/02/06

import java.util.*;
import java.io.*;

public class Main {
    static int[][] round = {{1,2,3,4}, {0,2,4,5}, {0,1,3,5}};
    static int[][] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int dice = Integer.parseInt(br.readLine());
        arr = new int[dice][6];
        int best = 0;

        StringTokenizer st;
        for(int i=0; i<dice; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<6; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<6; i++){
            int score = 0;
            int next = arr[0][i];
            for(int j=0; j<dice; j++){
                int nRound = chk_round(j, next);
                if(nRound==-1){
                    continue;
                }
                int now = 0;
                for(int k=0; k<4; k++){
                    now = Math.max(now, arr[j][round[nRound][k]]);
                }
                score += now;
                next = chk_next(j, next);
            }
            best = Math.max(best, score);
        }

        bw.write(Integer.toString(best));
        bw.close();
        br.close();
    }

    public static int chk_next(int n, int s){
        for(int i=0; i<6; i++){
            if(arr[n][i]==s){
                if(i==0){
                    return arr[n][5];
                } else if(i==1){
                    return arr[n][3];
                } else if(i==2){
                    return arr[n][4];
                }else if(i==3){
                    return arr[n][1];
                }else if(i==4){
                    return arr[n][2];
                }else{
                    return arr[n][0];
                }
            }
        }
        return -1;
    }

    public static int chk_round(int n, int s){
        for(int i=0; i<6; i++){
            if(arr[n][i] == s){
                if(i==0||i==5){
                    return 0;
                } else if(i==1||i==3){
                    return 1;
                }else{
                    return 2;
                }
            }
        }
        return -1;
    }
}

package beakjoon.d220217.q10157;
//https://www.acmicpc.net/problem/10157
//Solved : 22/02/17
import java.util.*;
import java.io.*;

public class Main {
    static int[][] D = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());       //가로, 세로 입력
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());        //번호 입력

        if(R*C<K) sb.append("0");                       //최대값보다 높으면 0 출력
        else{
            int nowR=1, nowC=1;
            int i=1, dist=0, rounds=1;
            while(true){
                if(nowR+D[dist][0]>R-rounds+1|| nowC+D[dist][1]<rounds || nowC+D[dist][1]>C-rounds+1) dist++;   //구석끝 마다 방향 변경
                if(dist==3&&nowR==rounds+1){                        //한바퀴 돌고 다시 위로 올라가야할때
                    rounds++;
                    dist=0;
                }
                if(i==K){                                           //K와 일치할 경우
                    sb.append(nowR).append(" ").append(nowC);
                    break;
                }
                nowR += D[dist][0];
                nowC += D[dist][1];
                i++;
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}

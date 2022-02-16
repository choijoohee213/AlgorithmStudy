package beakjoon.d220213.q2669;
//https://www.acmicpc.net/problem/2669
//Solved : 22/02/13

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[][] paper = new boolean[101][101];                  //모든 좌표
        StringTokenizer st;
        int rst=0;
        for(int tc=0; tc<4; tc++){                                  //4개의 사각형
            st = new StringTokenizer(br.readLine())
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for(int i=y1; i<y2; i++){
                for(int j=x1; j<x2; j++){
                    if(!paper[i][j]) {                              //만약 덮이지 않았다면 결과 +1, 덮임 체크
                        paper[i][j] = true;
                        rst++;
                    }
                }
            }
        }
        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}

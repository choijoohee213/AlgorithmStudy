package beakjoon.d220210.q2563;
//https://www.acmicpc.net/problem/2563
//Solved : 22/02/10

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[][] paper = new boolean[100][100];              //흰 도화지
        int a = Integer.parseInt(br.readLine());                //종이 갯수
        int rst = 0;                                            //검은 영역 너비

        StringTokenizer st;
        for(int i=0; i<a; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            for(int j=r; j<r+10; j++){                          //만약 이미 덮인 곳이면 패스
                for(int k=c; k<c+10; k++){                      //덮이지 않았으면 결과+1, 체크
                    if(!paper[j][k]){
                        paper[j][k] = true;
                        rst++;
                    }
                }
            }
        }
        bw.write(Integer.toString(rst));ㄴ
        bw.close();
        br.close();
    }
}

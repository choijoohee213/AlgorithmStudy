//https://www.acmicpc.net/problem/1080
//Solved : 22/03/16

import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int rst=0;
        boolean[][] m1 = new boolean[N][M];
        boolean[][] m2 = new boolean[N][M];

        String str;
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            for (int j = 0; j < M; j++) m1[i][j] = str.charAt(j) != '0';
        }
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            for (int j = 0; j < M; j++) m2[i][j] = str.charAt(j) != '0';
        }

        loop:for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(m1[i][j]!=m2[i][j]){
                    if(i>N-3||j>M-3){               //값이 다른다 3*3배열을 못만드는 경우 -1 선언언
                       rst = -1;
                        break loop;
                    }
                    for(int ii=0; ii<3;  ii++) for(int jj=0; jj<3; jj++) m1[i+ii][j+jj] = !m1[i+ii][j+jj];
                    rst++;
                }
            }
        }

        bw.write(Integer.toString(rst));
        bw.close();
        br.close();
    }
}
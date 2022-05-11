package com.younghwani.a220330;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main_bj_18111_마인크래프트 {
    static int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE, time=Integer.MAX_VALUE, floor;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // N,M,B(블럭수)
        int N=in[0],M=in[1],B=in[2];
        int[][] map = new int[N][M];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<M; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
                if(min>map[i][j]) min=map[i][j];
                if(max<map[i][j]) max=map[i][j];
            }
        }
        for (int f=min; f<=max; f++) { // 알맞는 층의 범위(min~max)
            int t=0, rem=B; // f층 계산에 소요되는 시간, 남은 블럭 수
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    int d=map[i][j]-f;
                    if(d>0){ t+=Math.abs(d)*2; rem+=Math.abs(d); } // 층을 증가시켜야 하는 경우
                    else if (d<0) { t+=Math.abs(d); rem-=Math.abs(d); } // 층을 감소시켜야 하는 경우
                }
            }
            if(rem>=0) { if(Math.min(t, time)==t) { time=t; floor=f; } } // 남은 블럭수가 양수일 때 그 층의 계산 결과가 유효함.
        }
        System.out.print(time+" "+floor);
        br.close();
    }
}
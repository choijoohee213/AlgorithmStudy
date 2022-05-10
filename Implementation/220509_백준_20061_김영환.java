package com.younghwani.a220509;

import java.io.*;
import java.util.*;
/*
빨간 보드에 놓으면 파란, 초록 보드로 이동함.
1*1, 1*2, 2*1 모양 블록 존재
블록 없애는 순서는 가득찬 경우(점수 증가)를 먼저 고려하고, 이후에 연한 파랑, 초록 보드 부분을 고려한다.
 */
class Main_bj_20061_모노미노도미노2 {
    static int score, blue[][], green[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        score = 0;
        blue = new int[4][6];
        green = new int[6][4];
        int N = Integer.parseInt(br.readLine());

        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 위치 지정
            int bIdx=0, gIdx=0;
            switch(t) { // 범위 안 넘어가고, 목적지가 비었을 때, 갈 수 있는 데까지 go
                case 1 : // 하나짜리
                    blue[x][0] = 1; green[0][y] = 1; // 파, 초 보드에도 블럭 지정
                    bIdx = 1;
                    while(bIdx < 6 && blue[x][bIdx] == 0) {
                        blue[x][bIdx-1] = 0; blue[x][bIdx] = 1; // 이동
                        bIdx++;
                    }
                    gIdx = 1;
                    while(gIdx < 6 && green[gIdx][y] == 0) {
                        green[gIdx-1][y] = 0; green[gIdx][y] = 1; // 이동
                        gIdx++;
                    }
                    break;
                case 2 : // 가로
                    blue[x][0] = 1; blue[x][1] = 1; // 파 보드 블럭 지정
                    green[0][y] = 1; green[0][y+1] = 1; // 초 보드에 블럭 지정
                    bIdx = 2;
                    while(bIdx < 6 && blue[x][bIdx] == 0) {
                        blue[x][bIdx-2] = 0; blue[x][bIdx] = 1; // 이동
                        bIdx++;
                    }
                    gIdx = 1;
                    while(gIdx < 6 && green[gIdx][y] == 0 && green[gIdx][y+1] == 0) {
                        green[gIdx-1][y] = 0; green[gIdx][y] = 1; // 이동
                        green[gIdx-1][y+1] = 0; green[gIdx][y+1] = 1; // 이동
                        gIdx++;
                    }
                    break;
                case 3 : // 세로
                    blue[x][0] = 1; blue[x+1][0] = 1; // 파 보드 블럭 지정
                    green[0][y] = 1; green[1][y] = 1; // 초 보드에 블럭 지정
                    bIdx = 1;
                    while(bIdx < 6 && blue[x][bIdx] == 0 && blue[x+1][bIdx] == 0) {
                        blue[x][bIdx-1] = 0; blue[x][bIdx] = 1; // 이동
                        blue[x+1][bIdx-1] = 0; blue[x+1][bIdx] = 1; // 이동
                        bIdx++;
                    }
                    gIdx = 2;
                    while(gIdx < 6 && green[gIdx][y] == 0) {
                        green[gIdx-2][y] = 0; green[gIdx][y] = 1; // 이동
                        gIdx++;
                    }
                    break;
            }
            // 파란 보드 열 다 채워졌는지 확인 - 연한 파랑 고려
            for(int i = 5; i >= 2; i--) {
                int cnt = 0;
                for(int j = 0; j < 4; j++) {
                    if(blue[j][i] == 0) break;
                    cnt++;
                }
                if(cnt == 4) { // 다 채워졌으면 열 삭제 및 점수 획득
                    score++;
                    delCol(i);
                    i++;
                }
            }
            // 초록 보드 행 다 채워졌는지 확인 - 연한 초록 고려
            for(int i = 5; i >= 2; i--) {
                int cnt = 0;
                for(int j = 0; j < 4; j++) {
                    if(green[i][j] == 0) break;
                    cnt++;
                }
                if(cnt == 4) { // 다 채워졌으면 행 삭제 및 점수 획득
                    score++;
                    delRow(i);
                    i++;
                }
            }
            // 이동
            mvGreen();
            mvBlue();
        }
        //파, 초 보드에 남은 블럭 수
        int res = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 6; j++) {
                if(blue[i][j] == 1) res++;
                if(green[j][i] == 1) res++;
            }
        }
        System.out.println(score);
        System.out.print(res);
    }
    static void delCol(int c) { // 파
        for(int j = c; j > 0; j--) {
            for(int i = 0; i < 4; i++) {
                blue[i][j] = blue[i][j-1];
            }
        }
    }
    static void delRow(int r) { // 초
        for(int i = r; i > 0; i--) {
            for(int j = 0; j < 4; j++) {
                green[i][j] = green[i-1][j];
            }
        }
    }
    static void mvGreen() {
        int cnt = 0; // 사라질 양
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++)
                if(green[i][j] == 1) {
                    cnt++;
                    break;
                }
        }
        // 밑으로 내림(연한 곳에 있으면 cnt 만큼 아래로 이동한다.)
        for(int i = 5; i >= 2; i--) for(int j = 0; j < 4; j++) green[i][j] = green[i-cnt][j]; // 진초
        for(int i = 0; i < 2; i++) for(int j = 0; j < 4; j++) green[i][j] = 0; // 연초
    }
    static void mvBlue() {
        int cnt = 0; // 사라질 양
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++)
                if(blue[j][i] == 1) {
                    cnt++;
                    break;
                }
        }
        // 밑으로 내림(연한 곳에 있으면 cnt 만큼 아래로 이동한다.)
        for(int i = 5; i >= 2; i--) for(int j = 0; j < 4; j++) blue[j][i] = blue[j][i-cnt]; // 진파
        for(int i = 0; i < 2; i++) for(int j = 0; j < 4; j++) blue[j][i] = 0; // 연파
    }
}

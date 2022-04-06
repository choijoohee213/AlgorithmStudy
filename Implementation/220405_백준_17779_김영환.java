package com.younghwani.a220405;

import java.io.*;
import java.util.*;

public class Main_bj_17779_게리맨더링2 {
    static int N, map[][], people, res=Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                people+=map[i][j];
            }
        }
        // 1. 기준점(r,c)과 경계의 길이 d1, d2를 정한다.
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if(r+d1+d2>=N) continue;
                        if(c-d1<0) continue;
                        if(c+d2>=N) continue;
                        solve(r, c, d1, d2);
                    }
                }
            }
        }
        System.out.print(res);
        br.close();
    }
    static void solve(int r, int c, int d1, int d2) {
        boolean[][] border = new boolean[N][N];
        // 2. 다음 칸은 경계선이다. 경계선을 계산한다.
        for (int i = 0; i <= d1; i++) {
            border[r+i][c-i]=true;
            border[r+d2+i][c+d2-i]=true;
        }
        for (int i = 0; i <= d2; i++) {
            border[r+i][c+i]=true;
            border[r+d1+i][c-d1+i]=true;
        }
        int[] div=new int[6];
        // 4. 5번 선거구에 포함되지 않은 구역(i, j)의 선거구 번호는 다음 기준을 따른다.
        // 1번 선거구
        for (int i = 0; i < r+d1; i++) {
            for (int j = 0; j <= c; j++) {
                if(border[i][j]) break;
                div[1]+=map[i][j];
            }
        }
        // 2번 선거구
        for (int i = 0; i <= r+d2; i++) {
            for (int j = N-1; j > c; j--) {
                if(border[i][j]) break;
                div[2]+=map[i][j];
            }
        }
        // 3번 선거구
        for (int i = r+d1; i < N; i++) {
            for (int j = 0; j < c-d1+d2; j++) {
                if(border[i][j]) break;
                div[3]+=map[i][j];
            }
        }
        // 4번 선거구
        for (int i = r+d2+1; i < N; i++) {
            for (int j = N-1; j >= c-d1+d2; j--) {
                if(border[i][j]) break;
                div[4]+=map[i][j];
            }
        }
        // 3. 경계선과 경계선의 안에 포함되어있는 곳은 5번 선거구다.
        int tmp = 0;
        for (int i = 1; i <= 4; i++) {
            tmp+=div[i];
        }
        div[5]=people-tmp;
        // 인구가 가장 많은 선거구와 가장 적은 선구구의 인구 차이의 최소값을 출력한다.
        Arrays.sort(div);
        tmp = div[5]-div[1];
        if(res>tmp) res=tmp;
    }
}

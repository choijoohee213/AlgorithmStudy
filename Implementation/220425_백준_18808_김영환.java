package com.younghwani.a220425;
import java.io.*;
import java.util.*;
/*
불필요한 행이나 열이 없어야 하고, 연결이 되어 있어야 한다.
가장 위쪽, 가장 왼쪽
 */
public class Main_bj_18808_스티커붙이기 {
    static int N, M, nr, nc, res, map[][], sticker[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 노트북의 세로
        M = Integer.parseInt(st.nextToken()); // 노트북의 가로
        int K = Integer.parseInt(st.nextToken()); // 스티커 수
        map = new int[N][M];
        while(K-->0) {
            st = new StringTokenizer(br.readLine(), " ");
            int R = Integer.parseInt(st.nextToken()); // 스티커가 인쇄된 모눈종이의 행
            int C = Integer.parseInt(st.nextToken()); // 스티커가 인쇄된 모눈종이의 열
            sticker = new int[R][C]; // 스티커 정보
            int size = 0;
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < C; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                    if(sticker[i][j]==1) size++;
                }
            }
            nr=R; nc=C; // 범위 카피해 사용
            solve(size, R, C);
        }
        System.out.print(res);
    }
    static void solve(int size, int R, int C) {
        for (int d = 0; d < 4; d++) { // 시계방향 회전
            for (int i = 0; i < N; i++) { // 스티커 붙이기
                for (int j = 0; j < M; j++) {
                    if(i+nr>N || j+nc>M) continue; // 새 좌표의 범위 초과
                    boolean isOk = true;
                    loop:for (int h = 0; h < nr; h++) {
                        for (int k = 0; k < nc; k++) {
                            if(map[i+h][j+k]==1 && sticker[h][k]==1) { // 모눈 칸이 겹치는 경우, 못 붙임
                                isOk = false;
                                break loop;
                            }
                        }
                    }
                    if(isOk) { // 노트북에 스티커 붙일 수 있는 경우
                        for (int h = 0; h < nr; h++) {
                            for (int k = 0; k < nc; k++) {
                                if(sticker[h][k] == 1) map[i+h][j+k] = 1;
                            }
                        }
                        res += size;
                        return;
                    }
                }
            }
            if(d!=3) rotate(d, R, C); // 3일때는 회전이 의미없음.(더 이상 붙일 곳 없으니)
        }
    }
    static void rotate(int d, int R, int C) {
        int ans[][]; // 회전 결과
        int r = nr, c = nc;
        if(d==1) { // 두 번 돌리면 R, C 길이 같음
            nr = R; nc = C; ans = new int[nr][nc];
        } else {
            nr = C; nc = R; ans = new int[nr][nc];
        }
        for (int i = 0; i < r; i++) { // 회전
            for (int j = 0; j < c; j++) {
                ans[j][nc-1-i] = sticker[i][j];
            }
        }
        // 적용
        sticker = new int[nr][nc];
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                sticker[i][j]=ans[i][j];
            }
        }
    }
}

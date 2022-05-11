package com.younghwani.a220301;

import java.io.*;
import java.util.stream.Stream;
public class Main_bj_12100_2024Easy {
    static int N, max=Integer.MIN_VALUE, di[]={-1,0,1,0}, dj[]={0,1,0,-1}, D[]=new int[5], arr[][], tmp[][];
    static boolean[][] V;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N]; tmp = new int[N][N]; // arr, arr을 복사해 사용할 tmp 초기화
        for (int i = 0; i < N; i++) arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        perm(0);
        System.out.println(max);
        br.close();
    }
    // 순열 - 모든 경우에 대한 조합
    static void perm(int cnt) {
        if (cnt == 5) {route(); return;}
        for (int i = 0; i < 4; i++) {D[cnt]=i; perm(cnt + 1);}
    }
    // 위,아래,좌,우 방향의 5차례 순서에 대해 게임 진행
    static void route() { // 구한 순열인 num을 바탕으로 계산
        for (int i = 0; i < arr.length; i++) tmp[i]=arr[i].clone(); // 원본 유지 위해 복사 배열 사용
        for (int i = 0; i < D.length; i++) {
            V = new boolean[N][N];
            if(D[i]==0)      for (int h = 0; h < N; h++)    for (int k = 0; k < N; k++) sol(h, k, D[i]); // 상
            else if(D[i]==1) for (int h = N-1; h >=0; h--)  for (int k = 0; k < N; k++) sol(k, h, D[i]); // 우
            else if(D[i]==2) for (int h = N-1; h >= 0; h--) for (int k = 0; k < N; k++) sol(h, k, D[i]); // 하
            else if(D[i]==3) for (int h = 0; h < N; h++)    for (int k = 0; k < N; k++) sol(k, h, D[i]); // 좌
        }
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) if(max<tmp[i][j]) max = tmp[i][j]; // 최대값 계산
    }
    static void sol(int r, int c, int d) {
        if (tmp[r][c]==0) return; // 이동할게 없음
        while (true) {
            int ni = r + di[d], nj = c + dj[d];
            if(ni<0||nj<0||ni>=N||nj>=N||V[ni][nj]) break;
            if(tmp[ni][nj]==tmp[r][c]) {
                V[ni][nj] = true;
                tmp[ni][nj] *= 2; tmp[r][c]=0; // 이동
                break;
            } else if(tmp[ni][nj]!=0) break; // 다른 값 있으면 못 옮김
            tmp[ni][nj]=tmp[r][c]; tmp[r][c]=0; // 이동
            r = ni; c = nj;
        }
    }
}

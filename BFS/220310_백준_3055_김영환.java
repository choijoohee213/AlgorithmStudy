package com.younghwani.a220310;

import java.io.*;
import java.util.*;
public class Main_bj_3055_탈출 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int R = tmp[0], C = tmp[1], di[]={-1, 0, 1, 0}, dj[]={0, 1, 0, -1};
        char[][] arr = new char[R][C];      // 입력배열
        boolean[][] V = new boolean[R][C];  // 고슴도치 방문여부
        Queue<int[]> q = new ArrayDeque<>(), wq = new ArrayDeque<>();   // 고슴도치 이동, 물 퍼짐
        for (int i = 0; i < R; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                arr[i][j] = c[j];
                if(arr[i][j]=='S') { V[i][j]=true; q.offer(new int[]{i, j, 1});}    // 출발 위치
                if(arr[i][j]=='*') { wq.offer(new int[]{i, j}); }                   // 초기 물 위치
            }
        }
        while (true) {
            int wSize = wq.size();
            for (int t = 0; t < wSize; t++) { // 물 퍼짐
                int[] cur = wq.poll();
                for (int i = 0; i < 4; i++) {
                    int wi = cur[0] + di[i], wj = cur[1] + dj[i];
                    if(wi<0||wj<0||wi>=R||wj>=C||arr[wi][wj]!='.') continue;
                    wq.offer(new int[]{wi, wj});
                    arr[wi][wj]='*';
                }
            }
            int size = q.size();
            if(size==0) break; // 더 이상 이동할 곳 없으면 종료
            for (int t = 0; t < size; t++) { // 고슴도치 이동
                int[] cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int ni = cur[0] + di[i], nj = cur[1] + dj[i];
                    if(ni<0||nj<0||ni>=R||nj>=C) continue;
                    if(arr[ni][nj]=='D') { System.out.println(cur[2]); return; } // 목적지(비버 굴) 도착하면 종료
                    if(V[ni][nj]||arr[ni][nj]!='.') continue;
                    q.offer(new int[]{ni, nj, cur[2]+1});
                    V[ni][nj]=true;
                }
            }
        }
        System.out.println("KAKTUS");
        br.close();
    }
}

package com.younghwani.a220308;

import java.io.*;
import java.util.*;
/*
8*8 체스판. 빈칸 or 벽. 좌측아래->우측위. 1초마다 모든 박이 아래 행으로. 가장 아래면 벽 사라짐. 8방이동 or 제자리. 캐릭터 이동 -> 벽이동.
 */
public class Main_bj_16954_움직이는미로탈출 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int di[]={-1,-1,-1,0,1,1,1,0,0}, dj[]={-1,0,1,1,1,0,-1,-1,0};
        char[][] arr = new char[8][8];
        for (int i = 0; i < 8; i++) arr[i]=br.readLine().toCharArray();
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{7, 0, 0}); // r, c, cnt
        while (!q.isEmpty()) {
            int size=q.size();
            boolean[][] V = new boolean[8][8];
            for (int t = 0; t < size; t++) { // 단위시간(1초)에 이동 가능한 횟수만큼 반복
                int r=q.peek()[0], c=q.peek()[1], cnt=q.poll()[2];
                if(arr[r][c]=='#') continue; // 벽을 만나면 해당 경우 무시
                if(r==0&&c==7) { System.out.println(1); return; } // 이동 가능
                for (int i = 0; i < 9; i++) { // 캐릭터 이동
                    int ni = r+di[i], nj = c+dj[i];
                    if(ni<0||nj<0||ni>=8||nj>=8||V[ni][nj]||arr[ni][nj]=='#') continue; // 이동 가능 범위 안에 들 때
                    q.offer(new int[]{ni,nj,cnt+1});
                    V[ni][nj]=true;
                }
            }
            for (int i = 7; i >=0 ; i--) { // 벽 이동
                for (int j = 0; j <= 7; j++) {
                    if(i==0) arr[i][j]='.';
                    else arr[i][j] = arr[i-1][j];
                }
            }
        }
        System.out.println(0);
        br.close();
    }
}

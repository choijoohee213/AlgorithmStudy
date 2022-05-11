package com.younghwani.a220313;

import java.io.*;
import java.util.*;

public class Main_bj_6087_레이저통신 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        char[][] arr = new char[H][W];
        int[][] V = new int[H][W];
        int r1=-1, c1=-1, r2=-1, c2=-1, di[]={-1,0,1,0}, dj[]={0,1,0,-1}, res=0;
        for (int i = 0; i < H; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if(arr[i][j]=='C') {
                    if(r1==-1) {r1=i; c1=j;}
                    else {r2=i; c2=j;}
                }
            }
            Arrays.fill(V[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[2]-o2[2]));
        pq.offer(new int[]{r1, c1, 0, -1}); // r,c,cnt,direction
        V[r1][c1]=0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[0]==r2&&cur[1]==c2) {res=cur[2]; break;}
            for (int i = 0; i < 4; i++) {
                int ni = cur[0] + di[i];
                int nj = cur[1] + dj[i];
                if(ni<0||nj<0||ni>=H||nj>=W||arr[ni][nj]=='*') continue;
                if(cur[3]==i || cur[3]==-1) { // 방향 같거나, 초기 위치일 경우
                    if(V[ni][nj]<cur[2]) continue;
                    V[ni][nj]=cur[2];
                    pq.offer(new int[]{ni, nj, cur[2], i});
                } else { // 방향을 변경하는 경우, 거울 설치
                    if(V[ni][nj]<cur[2]+1) continue;
                    V[ni][nj]=cur[2]+1;
                    pq.offer(new int[]{ni, nj, cur[2]+1, i});
                }
            }
        }
        System.out.print(res);
        br.close();
    }
}

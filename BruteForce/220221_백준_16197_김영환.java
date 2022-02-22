package com.younghwani.a220221;

import java.io.*;
import java.util.*;

public class Main_bj_16197_두동전 {
    static final int[] di = {-1, 0, 1, 0}; // 상우하좌
    static final int[] dj = {0, 1, 0, -1};
    static int N, M, res=Integer.MAX_VALUE;
    static char[][] arr;
    static int[][] coin = new int[2][2];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_16197.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) if(arr[i][j]=='o') coin[idx++] = new int[]{i, j};
        }
        dfs(1, coin[0][0], coin[0][1], coin[1][0], coin[1][1]);
        System.out.println(res==Integer.MAX_VALUE ? -1 : res); // 모두 만족 못하면 -1 출력
        br.close();
    }

    static void dfs(int depth, int r1, int c1, int r2, int c2) {
        if (depth>=res || depth>10) { // 가지치기
            return; // 10회 이상이면 그만
        }

        for (int i = 0; i < 4; i++) {
            int ni1 = r1 + di[i], nj1 = c1 + dj[i];
            int ni2 = r2 + di[i], nj2 = c2 + dj[i];

            int cnt=0;
            if (ni1<0 || nj1<0 || ni1 >=N || nj1 >= M) cnt++;
            if (ni2<0 || nj2<0 || ni2 >=N || nj2 >= M) cnt++;
            if (cnt==2) continue; // 동시에 떨어짐
            if (cnt==1) { // 최소횟수를 출력
                if(res>depth) res=depth;
                return;
            }

            // 두 동전 위치 같아지면
            if(ni1==ni2 && nj1==nj2) continue; // 건너뜀

            // 벽 고려
            if(arr[ni1][nj1]=='#') { ni1=r1; nj1=c1; }
            if(arr[ni2][nj2]=='#') { ni2=r2; nj2=c2; }

            dfs(depth+1, ni1, nj1, ni2, nj2);
        }
    }
}

package com.younghwani.a220307;

import java.io.*;
import java.util.*;
public class Main_bj_14442_벽부수고이동하기2 {
    static int N, M, K, arr[][], min=Integer.MAX_VALUE, di[]={-1, 0, 1, 0}, dj[]={0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                arr[i][j]=Integer.parseInt(String.valueOf(tmp[j]));
            }
        }
        bfs();
        System.out.println(min==Integer.MAX_VALUE? -1 : min);
        br.close();
        br.close();
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] V = new boolean[N][M][K+1];
        V[0][0][0]=true;
        q.offer(new int[]{0, 0, 1, 0}); // r, c, cnt, broken
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == N - 1 && cur[1] == M - 1) {
                min = Math.min(min, cur[2]);
                return;
            }
            loop:for (int i = 0; i < 4; i++) {
                int ni = cur[0] + di[i], nj = cur[1] + dj[i];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                if (arr[ni][nj]==1 && cur[3]<K && !V[ni][nj][cur[3]]) { // 벽을 만났고, 이전에 벽을 K번 이상 깬적 없고
                    V[ni][nj][cur[3]] = true;
                    q.offer(new int[]{ni, nj, cur[2] + 1, cur[3]+1});
                } else if (arr[ni][nj] == 0 && !V[ni][nj][cur[3]]) {
                    V[ni][nj][cur[3]] = true;
                    q.offer(new int[]{ni, nj, cur[2]+1, cur[3]});
                }
            }
        }
    }
}

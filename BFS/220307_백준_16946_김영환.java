package com.younghwani.a220307;

import java.io.*;
import java.util.*;

public class Main_bj_16946_벽부수고이동하기4 {
    static int N, M, arr[][], blank[][], di[] = {-1, 0, 1, 0}, dj[] = {0, 1, 0, -1};
    static HashMap<Integer, Integer> blankSize = new HashMap<>(); // 빈공간의 크기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M]; // 입력
        blank = new int[N][M];   // 벽: 0, 빈공간: >0
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(String.valueOf(tmp[j]));
        }
        int id=1;
        for(int i=0;i<N;i++)for(int j=0;j<M;j++)if(arr[i][j]==0&&blank[i][j]==0){blankSize.put(id,bfs(i,j,id)); id++;}
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(blank[i][j]!=0) sb.append(0); // arr[i][j]가 0인 칸
                else sb.append(getSize(i, j)); // arr[i][j]가 1인 벽인 칸
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
    static int getSize(int r, int c) {
        int size=1;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int ni = r+di[i], nj = c+dj[i];
            if(ni<0||nj<0||ni>=N||nj>=M|| blank[ni][nj]==0) continue;
            set.add(blank[ni][nj]);
        }
        for(int bs: set) size += blankSize.get(bs); // 인접한 빈공간의 크기만큼을 더해준다.
        return size%10;
    }
    static int bfs(int r, int c, int id) {
        Queue<int[]> q = new ArrayDeque<>();
        int cnt = 1;
        blank[r][c] = id;
        q.offer(new int[]{r, c}); // r, c
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ni = cur[0] + di[i], nj = cur[1] + dj[i];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M || blank[ni][nj] != 0 || arr[ni][nj] == 1) continue;
                blank[ni][nj] = id;
                cnt++;
                q.offer(new int[]{ni, nj});
            }
        }
        return cnt;
    }
}

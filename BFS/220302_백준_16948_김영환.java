package com.younghwani.a220302;

import java.io.*;
import java.util.*;
import java.util.stream.*;
public class Main_bj_16948_데스나이트 {
    static int N, in[], di[]={-2,-2,0,0,2,2}, dj[]={-1,1,-2,2,-1,1};
    static boolean[][] V;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        in = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        bfs();
        br.close();
    }
    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        V = new boolean[N][N];
        V[in[0]][in[1]]=true;
        q.offer(new int[]{0, in[0], in[1]});
        while (!q.isEmpty()){
            int[] cur = q.poll();
            for (int i = 0; i < 6; i++) {
                int ni = cur[1]+di[i], nj = cur[2]+dj[i];
                if(ni<0||nj<0||ni>=N||nj>=N||V[ni][nj]) continue;
                if(ni==in[2]&&nj==in[3]) {System.out.println(cur[0]+1); return;}
                V[ni][nj]=true;
                q.offer(new int[]{cur[0]+1, ni, nj});
            }
        }
        System.out.println(-1);
    }
}

package com.younghwani.a220328;

import java.io.*;
import java.util.*;
public class Main_bj_15684_사다리조작 {
    static int N, M, H, map[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H+1][N];
        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c]=1; map[r][c+1]=-1; // 좌측:-1, 우측:1
        }
        for (int i = 0; i <= 3; i++) if(dfs(0, 0, i)) {System.out.print(i); return;}
        System.out.print(-1);
        br.close();
    }
    static boolean dfs(int depth, int cnt, int goal) {
        if(cnt==goal) {
            for (int i = 0; i < N; i++) {
                int r=0, c=i;
                while (r<=H) {
                    if(map[r][c]==1) c++;
                    else if(map[r][c]==-1) c--;
                    r++;
                }
                if(i!=c) return false;
            }
            return true;
        }
        for (int i = depth; i < H; i++) {
            for (int j = 0; j < N-1; j++) {
                if (map[i][j]!=0||map[i][j+1]!=0) continue; // 사다리 발판 이미 있을 때
                map[i][j]=1; map[i][j+1]=-1; // 사다리 발판 추가
                if(dfs(i, cnt+1, goal)) return true;
                map[i][j]=0; map[i][j+1]=0; // 사다리 발판 원상복구
            }
        }
        return false;
    }
}

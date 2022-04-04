package com.younghwani.a220404;

import java.io.*;

public class Main_bj_2239_스토쿠 {
    static int map[][];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map=new int[9][9];
        for (int i = 0; i < 9; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) map[i][j]=c[j]-'0';
        }
        dfs(0);
        br.close();
    }
    static void dfs(int depth) {
        if(depth==81) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) sb.append(map[i][j]);
                sb.append("\n");
            }
            System.out.print(sb.toString());
            System.exit(0);
        }
        int r = depth/9, c=depth%9; // 계산할 순서의 r, c
        if (map[r][c]!=0) dfs(depth + 1); // 이미 푼 경우면 다음으로 넘김
        else {
            for (int i = 1; i <= 9; i++) {
                if(!check(r, c, i)) continue;
                map[r][c]=i;
                dfs(depth + 1);
                map[r][c]=0;
            }
        }
    }
    static boolean check(int r, int c, int num) {
        // row, col
        for (int i = 0; i < 9; i++) if(map[r][i]==num||map[i][c]==num) return false;
        // block
        int nr=r/3*3, nc=c-c%3;
        for (int i = nr; i < nr+3; i++) {
            for (int j = nc; j < nc+3; j++) {
                if(map[i][j]==num) return false;
            }
        }
        return true;
    }
}

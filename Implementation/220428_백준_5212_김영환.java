package com.younghwani.a220508;
import java.io.*;
import java.util.*;
class Main_bj_5212_지구온난화 {
    static int di[]={-1, 0, 1, 0}, dj[]={0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();

        char[][] land = new char[R][C];
        int sr=Integer.MAX_VALUE, er=Integer.MIN_VALUE, sc=Integer.MAX_VALUE, ec=Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') land[i][j] = '.';
                else {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int ni=i+di[d], nj=j+dj[d];
                        if (ni<0||ni>=R||nj<0||nj>=C||map[ni][nj]=='.') cnt++;
                    }
                    if (cnt < 3) {
                        land[i][j] = 'X';
                        sr = Math.min(sr, i); er = Math.max(er, i);
                        sc = Math.min(sc, j); ec = Math.max(ec, j);
                    } else land[i][j] = '.';
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = sr; i <= er; i++) {
            for (int j = sc; j <= ec; j++) {
                sb.append(land[i][j]);
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }
}

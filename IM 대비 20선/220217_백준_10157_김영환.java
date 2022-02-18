package com.younghwani.a220214;

import java.util.*;
import java.io.*;

public class Main_bj_10157_자리배정 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int C = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        // 좌석 배정 안되는 경우
        if(K>C*R) {
            System.out.println(0);
            System.exit(0);
        }
        // 좌석 배정
        boolean[][] seats = new boolean[R][C];
        int cnt = 1, idx=0;
        int r=R-1, c=0;
        int[] di = new int[]{-1, 0, 1, 0}; // 상,우,하,좌
        int[] dj = new int[]{0, 1, 0, -1};
        while (cnt != K) {
            seats[r][c] = true;
            int ni = r + di[idx], nj = c + dj[idx];
            if (ni < 0 || ni > R - 1 || nj < 0 || nj > C - 1 || seats[ni][nj]) {
                idx = ++idx % 4;
                ni = r + di[idx]; nj = c + dj[idx];
                continue;
            }
            r = ni; c = nj;
            cnt++;
        }
        System.out.println((c+1) + " " + (R-r));
        br.close();
    }
}

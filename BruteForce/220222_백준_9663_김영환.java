package com.younghwani.a220222;

import java.io.*;
import java.util.*;

/*
체스 규칙을 알아야 한다. 하지만 나는 모른다.....
퀸이 서로 공격할 수 없도록 배치?
퀸의 이동 : 상하좌우 및 대각선으로 거리 제한 없이 이동 가능(한번에 한 방향으로 이동함)
조건 : 열이 다를 때, 같은 행에 있으면 안되고, 대각선에 있으면 안된다.
 */
public class Main_bj_9663_NQueen {
    static int N, Q[], cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Q = new int[N]; // 행을 index로, 열을 저장
        NQueen(0);
        System.out.println(cnt);
        br.close();
    }
    static void NQueen(int depth) {
        if(depth==N) { cnt++; return; }
        for (int i = 0; i < N; i++) {
            Q[depth] = i; // 행 저장
            if(!isOk(depth)) continue;
            NQueen(depth+1); // 열 이동
        }
    }
    static boolean isOk(int c) {
        for (int i = 0; i < c; i++) {
            // c를 기준으로 이전 depth 번째 r에 퀸 있는 경우
            if (Q[c]==Q[i]) return false;
            // 대각선에 위치하는 경우
            if (Math.abs(c-i)==Math.abs(Q[c]-Q[i])) return false;
        }
        return true;
    }
}

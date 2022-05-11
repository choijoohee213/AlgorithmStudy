package com.younghwani.a220405;

import java.io.*;
import java.util.*;
/*
3 2
1 2 1 2 1 2
>>> 2
3 6
10 10 10 10 10 10
>>> 31
4 5
10 1 10 6 3 4 8 2
>>> 24
 */
public class Main_bj_20055_컨베이어벨트위의로봇 {
    static int N, K, A[], step;
    static boolean[] robots;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[2*N];
        robots = new boolean[2*N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N * 2; i++) A[i] = Integer.parseInt(st.nextToken());
        while (canRotate()) {
            // 1. 컨베이어 및 로봇 돌리기
            int tmp = A[N*2-1];
            for (int i = N*2-1; i > 0; i--) A[i] = A[i - 1];
            A[0]=tmp;
            for (int i = N * 2 - 1; i > 0; i--) robots[i] = robots[i - 1];
            robots[0]=false;
            robots[N-1]=false; // N번째 위치 도착 시 내린다.
            // 2. 로봇 위치 옮기기
            for (int i = N-1; i > 0; i--) {
                if(robots[i-1] && !robots[i] && A[i]>=1) {
                    robots[i] = robots[i - 1];
                    robots[i-1]=false;
                    A[i]--;
                }
            }
            // 3. 로봇 올리기
            if(A[0]>=1) {
                robots[0]=true;
                A[0]--;
            }
            step++;
        }
        System.out.print(step);
        br.close();
    }
    static boolean canRotate() {
        int cnt=0;
        for (int i = 0; i < A.length; i++) {
            if(A[i]<=0) cnt++;
        }
        if(cnt>=K) return false;
        return true;
    }
}

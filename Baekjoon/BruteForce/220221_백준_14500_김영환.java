package com.younghwani.a220221;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

// 크기(깊이)가 4가 되는 때까지 dfs 돌린다.
// dfs로 돌리면 ㅏㅓㅗㅜ 모양 탐색 불가 - 따로 고려해준다.
public class Main_bj_14500_테트로미노 {
    static final int[] di = {-1, 0, 1, 0}; // 상우하좌
    static final int[] dj = {0, 1, 0, -1};
    static int N, M, ans;
    static int[][] arr;
    static boolean[][] V;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_14500.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        V = new boolean[N][M];
        for (int i = 0; i < N; i++) arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //System.out.println(Arrays.deepToString(arr));
        ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(0, i, j, 0);
                rem(i, j);
            }
        }
        System.out.println(ans);
        br.close();
    }

    static void dfs(int depth, int r, int c, int sum) {
        if (depth == 4) {
            if(ans<sum) ans=sum;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ni = r + di[i];
            int nj = c + dj[i];
            if (ni<0||nj<0||ni>=N||nj>=M||V[ni][nj]) continue;
            V[ni][nj] = true;
            dfs(depth+1, ni, nj, sum+arr[ni][nj]);
            V[ni][nj] = false;
        }
    }

    // 상,우,하,좌,가운데의 합에서 상,우,하,좌 중 작은 것 제외하기
    static void rem(int r, int c) {
        int tmp = arr[r][c]; // 기본값: 가운데
        int isOkCnt = 4; // 가운데 제외한 상,우,하,좌 값 있어야 함
        int del = Integer.MAX_VALUE; // 상,우,하,좌 중 최소
        for (int i = 0; i < 4; i++) {
            int ni = r + di[i];
            int nj = c + dj[i];
            if (ni<0||nj<0||ni>=N||nj>=M) {
                isOkCnt--;
                continue;
            }
            tmp += arr[ni][nj];
            if(del>arr[ni][nj]) del = arr[ni][nj];
        }
        if(isOkCnt<3) return;
        if(isOkCnt==4) tmp -= del;
        if(ans<tmp) ans=tmp;
    }
}

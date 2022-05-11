package com.younghwani.a220210;

import java.util.*;
public class Main_bj_14225_부분수열의합 {
    static int N;
    static int[] inputs;
    static boolean[] visited = new boolean[2000010];
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        inputs = new int[N];
        for (int i = 0; i < N; i++) inputs[i] = sc.nextInt();
        dfs(0, 0); // 모든 부분집합 요소들의 합을 구한다.
        int idx = 1;
        while (visited[idx++]) {}
        System.out.println(--idx);
        sc.close();
    }
    static void dfs(int idx, int sum) {
        if(idx==N) {
            visited[sum] = true;
            return;
        }
        // 모든 부분집합 구함
        dfs(idx+1, sum+inputs[idx]); // 자기 자신 포함
        dfs(idx+1, sum); // 자기 자신 미포함
    }
}

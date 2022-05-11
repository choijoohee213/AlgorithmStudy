package com.younghwani.a220209;

import java.util.*;

public class Main_bj_1182_부분수열의합 {
    static int N, S, cnt;
    static int[] inputs;
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input_bj_1182.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        cnt = (S==0) ? -1:0; // S가 0이면 공집합도 포함되니 이를 고려함
        inputs = new int[N];
        for (int i = 0; i < N; i++) inputs[i] = sc.nextInt();
        dfs(0, 0);
        System.out.println(cnt);
        sc.close();
    }
    static void dfs(int idx, int sum) {
        if(idx==N) {
            if(sum==S) cnt++; // 총합이 S이면 카운트 증가
            return;
        }
        // 모든 부분집합 구함
        dfs(idx+1, sum+inputs[idx]); // 자기 자신 포함
        dfs(idx+1, sum); // 자기 자신 미포함
    }
}

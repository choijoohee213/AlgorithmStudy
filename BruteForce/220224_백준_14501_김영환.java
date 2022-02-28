package com.younghwani.a220224;

import java.io.*;
import java.util.*;

public class Main_bj_14501_퇴사 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+2]; // N이 7이면 1~7
        int[] P = new int[N+2];
        int[] dp = new int[N+2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = N; i > 0; i--) {
            // 상담 종료일이 퇴사일을 넘기는 날인 경우
            if(i+T[i] > N+1) dp[i] = dp[i + 1];
            // 상담 안할 때(다음 순서의 값과 변화 없음) vs 상담종료일의 금액과 그 상담의 금액
            else dp[i] = Math.max(dp[i+1], dp[i+T[i]]+P[i]);
        }
        System.out.println(dp[1]);
        br.close();
    }
}

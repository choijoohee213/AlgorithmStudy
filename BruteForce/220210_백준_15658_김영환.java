package com.younghwani.a220210;

import java.util.*;
import java.io.*;

public class Main_bj_15658_연산자끼워넣기2 {
    static int N, max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
    static int[] inputs;
    static int[] deli = new int[4];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inputs = new int[N];

        // 숫자 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) inputs[i] = Integer.parseInt(st.nextToken());

        // 연산자 입력받기
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) deli[i] = Integer.parseInt(st.nextToken());

        subSum(1, inputs[0]); // depth 1번부터 시작함. (*, / 연산 위해서)
        System.out.println(max);
        System.out.println(min);

        br.close();
    }

    static void subSum(int depth, int calc) {
        if (depth == N) {
            max = Math.max(max, calc);
            min = Math.min(min, calc);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if(deli[i]<=0) continue;    // 계산할 연산이 남아있지 않으면 넘어감
            deli[i]--;      // 연산했으니 해당 연산 했다는 표시
            if (i == 0) subSum(depth+1, calc+inputs[depth]);        // +
            else if (i == 1) subSum(depth+1, calc-inputs[depth]);   // -
            else if (i == 2) subSum(depth+1, calc*inputs[depth]);   // *
            else if (i == 3) subSum(depth+1, calc/inputs[depth]);   // /
            deli[i]++;      // 재귀 돌고 왔으니 다시 연산자 고려하기 위해 표시
        }
    }
}

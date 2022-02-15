package com.younghwani.a220211;

import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Main_bj_2491_수열 {
    static int N, cnt;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2491.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp(0);
        System.out.println(cnt);
        br.close();
    }

    static void dp(int start) { // DP는 Top-Down & Bottom-Up 방식인데 현재 코드와는 차이가 있음. 개선 필요하다 생각함.
        if (start==N) return;

        int plusCnt = 0;
        int minusCnt = 0;
        for (int i = start; i < N; i++) {
            plusCnt++;
            if(i+1<N && arr[i+1]<arr[i]) break;
        }
        for (int i = start; i < N; i++) {
            minusCnt++;
            if(i+1<N && arr[i+1]>arr[i]) break;
        }

        cnt = Math.max(cnt, Math.max(plusCnt, minusCnt));

        dp(start + 1);
    }
}

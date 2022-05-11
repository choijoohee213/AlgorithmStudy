package com.younghwani.a220212;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
연속적인 며칠 동안의 온도합이 가장 큰 값을 계산.

 */
public class Main_bj_2559_수열 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2559.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 측정 전체 날짜 수
        int K = Integer.parseInt(st.nextToken()); // 합을 구하기 위한 연속적인 날짜
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //System.out.println(Arrays.toString(arr));
        int res = Integer.MIN_VALUE;
        // 연속성을 갖으니 차례대로 K만큼 더한 후 max 계산
        for (int i = 0; i < N-K+1; i++) {
            int sum=0;
            for (int j = i; j < i+K; j++) sum += arr[j];
            res = Math.max(res, sum);
        }
        System.out.println(res);
        br.close();
    }
}

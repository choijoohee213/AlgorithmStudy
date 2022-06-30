package com.younghwani.a220630;

import java.io.*;
import java.util.*;

public class Main_bj_1654_랜선자르기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] lan = new int[K];
        long s = 1, e = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            lan[i] = Integer.parseInt(br.readLine());
            if(lan[i] > e) e = lan[i];
        }

        while (s <= e) {
            long m = (s + e) / 2;
            int cnt = 0;
            for (int i = 0; i < K; i++) {
                cnt += lan[i] / m;
            }
            if (cnt >= N) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }

        System.out.print(s - 1);
        br.close();
    }
}

package com.younghwani.a220708;

import java.io.*;
import java.util.*;

public class Main_bj_2343_기타레슨 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int s = Integer.MIN_VALUE, e = 0; // s, e 범위를 설정할 때 어려움을 겪었다. 문제를 잘 읽자!

        int[] ray = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            ray[i] = Integer.parseInt(st.nextToken());
            s = Math.max(s, ray[i]);
            e += ray[i];
        }

        while (s <= e) {
            int m = (s + e) / 2;
            int idx = 0, tmp = 0;
            for(int r: ray) {
                if (tmp + r > m) {
                    idx++;
                    tmp = 0;
                }
                tmp += r;
            }

            if (idx <= M - 1) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }

        System.out.println(s);
        br.close();
    }
}
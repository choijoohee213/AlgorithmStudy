package com.younghwani.a220714;

import java.io.*;
import java.util.*;
// 문제를 잘 읽자.... 여러 개의 테케...
public class Main_bj_3649_로봇프로젝트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null) {
            int X = Integer.parseInt(input) * 10000000;
            int N = Integer.parseInt(br.readLine());

            int[] L = new int[N];
            for (int i = 0; i < N; i++) L[i] = Integer.parseInt(br.readLine());
            Arrays.sort(L);

            String res = "danger";
            int s = 0, e = N - 1;
            while (s < e) {
                int sum = L[s] + L[e];

                if (sum > X) {
                    e--;
                } else if (sum < X) {
                    s++;
                } else {
                    res = "yes " + L[s] + " " + L[e];
                    break;
                }
            }

            System.out.println(res);
        }

        br.close();
    }
}

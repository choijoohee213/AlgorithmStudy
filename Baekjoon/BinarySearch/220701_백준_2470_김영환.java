package com.younghwani.a220705;

import java.io.*;
import java.util.*;

public class Main_bj_2470_두용액 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] solution = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solution);

        String res = "";
        int s = 0, e = N - 1;
        int abs = Integer.MAX_VALUE;

        while (s < e) {
            int tmp = solution[s] + solution[e];
            if (Math.abs(tmp) < abs) {
                abs = Math.abs(tmp);
                res = solution[s] + " " + solution[e];
            }
            if (tmp > 0) {
                e--;
            } else {
                s++;
            }
        }

        System.out.print(res);
        br.close();
    }
}

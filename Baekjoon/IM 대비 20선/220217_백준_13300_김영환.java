package com.younghwani.a220215;

import java.util.*;
import java.io.*;

public class Main_bj_13300_방배정 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] girl = new int[6];
        int[] boy = new int[6];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int gender = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken());
            if (gender==0) girl[grade-1]++;
            else boy[grade-1]++;
        }
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            cnt += Math.ceil(1.0*girl[i] / K);
            cnt += Math.ceil(1.0*boy[i] / K);
        }
        System.out.println(cnt);
        br.close();
    }
}

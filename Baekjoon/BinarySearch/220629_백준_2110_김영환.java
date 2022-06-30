package com.younghwani.a220630;

import java.io.*;
import java.util.*;

public class Main_bj_2110_공유기설치 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] home = new int[N];
        for (int i = 0; i < N; i++) home[i] = Integer.parseInt(br.readLine());
        Arrays.sort(home);

        int s = 1, e = home[N - 1] - home[0];
        int res = 0;

        while (s <= e) {
            int m = (s + e) / 2;
            int cnt = 1, prev = home[0]; // 초기 공유기 정보 (개수, 위치)

            for (int i = 1; i < N; i++) {
                if (home[i] - prev >= m) {
                    cnt++; // 공유기 설치
                    prev = home[i];
                }
            }

            // 이거 외않되? ㅜㅜ
//            if (cnt == C) {
//                res = m;
//                s = m + 1; // 최대 길이를 구해야하므로 계속 진행
//            } else if (cnt > C) {
//                s = m + 1;
//            } else if (cnt < C) {
//                e = m - 1;
//            }

            if (cnt >= C) {
                res = m;
                s = m + 1;
            } else {
                e = m - 1;
            }
        }

        System.out.print(res);
        br.close();
    }
}

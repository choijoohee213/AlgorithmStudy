package com.younghwani.a220323;

import java.io.*;
import java.util.*;

public class Main_bj_1783_병든나이트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int cnt = 1;
        if(N==2 && M>=3) {
            int tmp = (M-1)/2;
            if(tmp>=4) cnt = 4;
            else cnt += tmp;
        } else if(N>=3) {
            if(M<7) {
                cnt = M>4 ? 4: M;
            } else {
                cnt = M-2;
            }
        }

        System.out.println(cnt);
        br.close();
    }
}

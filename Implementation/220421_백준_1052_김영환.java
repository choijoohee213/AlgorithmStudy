package com.younghwani.a220421;

import java.io.*;
import java.util.*;
/*
N개의 물병을 가지고, 한번에 옮길 수 있는 개수인 K개 이하로 물을 재분배하여 옮기려고 한다.
먼저 같은 양의 물이 들어있는 물병 두 개를 고른다. 그 다음에 한 개의 물병에 다른 한 쪽에 있는 물을 모두 붓는다. 이 방법을 필요한 만큼 계속 한다.
상점에서 물병을 살 수도 있고, 기본적으로 1리터만큼 들어 있다.
 */
public class Main_bj_1052_물병 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N <= K) { System.out.print(0); return; } // 더 살 필요 없음

        int res = 0; // 사야하는 물병 수
        while (true) {
            int cnt = 0; // 물병 수
            int tmp = N;
            while (tmp!=0) {
                if(tmp%2==1) cnt++; // 물병 수 증가
                tmp /= 2;
            }
            if(cnt<=K) break; // K 이하로 재분배가 완료된 경우
            res++; N++; // 물병 구입이 필요한 경우
        }

        System.out.print(res);
        br.close();
    }
}

package com.younghwani.a220214;

import java.util.*;
import java.io.*;

/*
* java11로 제출하면 시간초과
* java8로 제출하면 성공..ㄷㄷ
* */
public class Main_bj_10158_개미 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/input_bj_10158.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 입력
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int p = Integer.parseInt(st.nextToken());   //col
        int q = Integer.parseInt(st.nextToken());   // row
        int t = Integer.parseInt(br.readLine());
        // 개미 이동
        int p_direction = ((p+t) / W) % 2;
        int q_direction = ((q+t) / H) % 2;
        // p, q 방향에 따른 위치부여
        if (p_direction == 0) p = (p+t) % W;
        else p = W - (p+t) % W;
        if (q_direction == 0) q = (q+t) % H;
        else q = H - (q+t) % H;
        System.out.println(p + " " + q);
        br.close();
    }
}


// time out error
//public class Main_bj_10158_개미 {
//    public static void main(String[] args) throws Exception {
//        //System.setIn(new FileInputStream("res/input_bj_10158.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        // 입력
//        int W = Integer.parseInt(st.nextToken());
//        int H = Integer.parseInt(st.nextToken());
//        st = new StringTokenizer(br.readLine(), " ");
//        int p = Integer.parseInt(st.nextToken());
//        int q = Integer.parseInt(st.nextToken());
//        int t = Integer.parseInt(br.readLine());
//        // 개미 이동
//        int[] di = new int[]{1, -1};
//        int[] dj = new int[]{1, -1};
//        int idxI=0, idxJ=0;
//        for (int i = 0; i < t; i++) {
//            int ni = p + di[idxI];
//            int nj = q + dj[idxJ];
//            if(ni<0 || ni>W) {
//                idxI = ++idxI % 2;
//                ni = p + di[idxI];
//            }
//            if (nj<0 || nj>H) {
//                idxJ = ++idxJ % 2;
//                nj = q + dj[idxJ];
//            }
//            p = ni;
//            q = nj;
//        }
//        System.out.println(p + " " + q);
//        br.close();
//    }
//}
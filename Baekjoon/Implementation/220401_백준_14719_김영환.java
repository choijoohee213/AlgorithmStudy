package com.younghwani.a220401;

import java.io.*;
import java.util.*;
// 컬럼을 한칸씩 이동하면서 좌우를 감싸는 최대 높이를 계산한 후, 물이 얼마나 찰 수 있는지 파악해 그 합을 구한다.
public class Main_bj_14719_빗물 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] map = new int[W];
        st=new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < W; i++) map[i]=Integer.parseInt(st.nextToken());
        int res=0;
        for (int i = 1; i < W-1; i++) {
            int cur=map[i], left=0, right=0;
            for (int j = i-1; j >=0; j--) if(left<map[j]) left=map[j];
            for (int j = i+1; j < W; j++) if(right<map[j]) right=map[j];
            if(left>cur && right > cur) res+=Math.min(left, right)-map[i];
        }
        System.out.print(res);
        br.close();
    }
}

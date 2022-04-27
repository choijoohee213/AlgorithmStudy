package com.younghwani.a220426;

import java.io.*;

public class Main_bj_2002_추월 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] S=new String[N], E=new String[N];
        for (int i = 0; i < N; i++) S[i]= br.readLine(); // 대근 - 터널 입구
        for (int i = 0; i < N; i++) E[i]= br.readLine(); // 영식 - 터널 출구
        boolean[] V = new boolean[N]; // 추월 가능성 판단
        int res = 0; // 추월로 여겨지는 차량 수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(S[i].equals(E[j])) { V[j]=true; break; } // 자신을 만나기 전의 차량들은 추월했을지도 모르는 차량들.
                if(V[j]) continue; // 이미 추월로 판단한 차량
                res++;
                V[j]=true; // 추월로 판단
            }
        }
        System.out.print(res);
        br.close();
    }
}

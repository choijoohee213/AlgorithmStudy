package com.younghwani.a220324;

import java.io.*;
import java.util.*;

public class Main_bj_1210_NMK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(M+K>N+1 || M*K<N) {
            System.out.print(-1);
            return;
        }

        int[] size = new int[M]; // 최대 길이만큼 부분수열 사이즈 결정
        int tmp=0, rem=0;
        N -= K;
        if(M>1) {tmp=N/(M-1); rem=N%(M-1);} // 제일 앞은 size를 K로 설정했으니 사이즈 결정 제외
        Arrays.fill(size, tmp);
        size[0]=K; // 감소하는 길이 만큼의 수열은 필요함.
        int idx = 1;
        while (rem-->0) size[idx++]+=1; // 남은 만큼 사이즈 업데이트

        idx=0;
        for (int i = 0; i < M; i++) {
            idx+=size[i]; // 계산할 부분수열의 인덱스 업데이트
            for (int j = idx-1; j >= idx-size[i]; j--) sb.append(j+1).append(" ");
        }

        System.out.print(sb.toString());
        br.close();
    }
}

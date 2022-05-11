package com.younghwani.a220222;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main_bj_16198_에너지모으기 {
    static int N, num[], max=Integer.MIN_VALUE, W[];
    static boolean[] V;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        num = new int[N];
        V = new boolean[N];
        perm(1);
        System.out.println(max);
        br.close();
    }
    static void perm(int cnt) {
        if(cnt==N-1) {
            num[N-1] = N-1;
            // num 배열에 순서 모두 마련해놓음.
            int[] tmpNum = new int[N];
            System.arraycopy(num, 0, tmpNum, 0, num.length);
            int tmpSum=0;
            for (int i = 1; i < N-1; i++) {
                int cIdx=-1;
                for (int j = 1; j < N-1; j++) if(tmpNum[j]==i) cIdx=j; // 현재 순서의 index
                int lIdx=cIdx-1, rIdx=cIdx+1; // 현재 순서의 좌, 우 index
                while (tmpNum[lIdx]==-1) lIdx--; //왼
                while (tmpNum[rIdx]==-1) rIdx++; //오
                tmpSum+= W[lIdx]*W[rIdx];
                tmpNum[cIdx] = -1;
            }
            if(max<tmpSum) max = tmpSum;
            return;
        }
        for (int i = 1; i < N-1; i++) {
            if(V[i]) continue;
            V[i] = true;
            num[cnt] = i;
            perm(cnt + 1);
            V[i]=false;
        }
    }
}

package com.younghwani.a220312;

import java.io.*;
import java.util.*;
public class Main_bj_1963_소수경로 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 에라토스테네스의 체
        boolean[] notPrime = new boolean[10000];
        notPrime[0] = notPrime[1] = true;
        for(int i=2; i<10000; i++) {
            if(!notPrime[i]) for(int j=i*i; j<10000; j+=i) notPrime[j]=true;
        }
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String[] tmp = br.readLine().split(" ");
            int A = Integer.parseInt(tmp[0]);
            int B = Integer.parseInt(tmp[1]);
            int res = Integer.MAX_VALUE;
            int[] digit = {1000, 100, 10, 1};
            Queue<int[]> q = new ArrayDeque<>();
            boolean[] V = new boolean[10000];
            q.offer(new int[]{A, 0}); // num, cnt
            V[A] = true;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                if (cur[0]==B) {res = Math.min(res, cur[1]); break;}
                for (int i = 0; i < 4; i++) {
                    int left = cur[0] / digit[i] / 10;
                    int right = cur[0] % digit[i];
                    for (int j = 0; j <= 9; j++) {
                        if(i==0 && j==0) continue;
                        int n = (left*digit[i]*10) + (j*digit[i]) + right;
                        if (!notPrime[n] && !V[n]) {
                            q.offer(new int[]{n, cur[1]+1});
                            V[n] = true;
                        }
                    }
                }
            }
            if(res==Integer.MAX_VALUE) sb.append("Impossible").append("\n");
            else sb.append(res).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}

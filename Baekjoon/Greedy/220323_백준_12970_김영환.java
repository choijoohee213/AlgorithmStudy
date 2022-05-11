package com.younghwani.a220323;

import java.io.*;
import java.util.*;
// 업데이트할 인덱스를 찾는 것이 유난히 어려웠던 문제
public class Main_bj_12970_AB {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (K==0) {
            for (int i = 0; i < N; i++) sb.append("A");
            System.out.print(sb.toString());
            return;
        }

        char[] arr = new char[N];

        int A = 1;
        while (A < N) {
            if(A * (N-A) < K) {
                A++;
                continue;
            }

            Arrays.fill(arr, 'B');
            for (int i = 0; i < A-1; i++) arr[i] = 'A';

            int idx = (N-1) - (K-(A-1)*(N-A)); // 업데이트할 인덱스 : (배열의 끝 - (모자란 정도 : K - (A-1의 개수 * B의 개수)))
            arr[idx] = 'A';

            break;
        }

        if(A==N) sb.append(-1);
        else for (char c: arr) sb.append(c);

        System.out.print(sb.toString());
        br.close();
    }
}

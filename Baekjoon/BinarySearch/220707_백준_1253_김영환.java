package com.younghwani.a220712;

import java.io.*;
import java.util.*;

// 투포인터로 풀이
public class Main_bj_1253_좋다 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        int res = 0; // 좋다 개수
        for (int i = 0; i < N; i++) { // 후보를 정한다. O(N)
            int s = 0, e = N - 1;
            int tmp = arr[i]; // 찾으려는 좋다 후보
            loop:while (true) { // 올바른 후보인지 결정한다. O(N)
                if(s==i) s++;
                else if (e==i) e--; // i번째 값을 찾는데 i번째 값을 사용하면 좋다가 될 수 없음.

                if(s >= e) break loop;

                int sum = arr[s] + arr[e];
                if (sum < tmp) {
                    s++;
                } else if (sum > tmp) {
                    e--;
                } else if (sum == tmp) { // 좋다 이면
                    res++;
                    break loop;
                }
            }
        }

        System.out.print(res);
        br.close();
    }
}

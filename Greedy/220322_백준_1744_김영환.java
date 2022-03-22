package com.younghwani.a220322;

import java.io.*;
import java.util.*;
/*
정렬을 이용한다.
1보다 작거나 같은 경우는 그냥 더하는게 좋을 듯 싶다.
그게 아니라면 값이 있을 때 곱하는 것이 더 이득이다.
 */
public class Main_bj_1744_수묶기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int lt1 = 0; // Lower than 1
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(arr[i]<1) lt1++;
        }
        Arrays.sort(arr);

        int res=0, idx=-1;
        while (++idx<lt1) {
            if(idx+1<lt1) res+=arr[idx]*arr[++idx];
            else res+=arr[idx];
        }
        idx=N;
        while (--idx>=lt1) {
            if(idx-1>=lt1&&arr[idx-1]!=1) res+=arr[idx]*arr[--idx];
            else res+=arr[idx];
        }

        System.out.println(res);
        br.close();
    }
}

//처음 풀이 - 틀림 -> 왜 아닌가 생각해보니 마이너스 부분도 곱했을 때 최대가 나오도록 작은 것부터 계산해주어야 함. +,- 부분 나눠서 계산
//        for (int i = 0; i < N; i++) {
//            if(arr[i]<=0) {
//                if(i+1<N) res += arr[i] * arr[++i];
//                else res += arr[i];
//            } else if(arr[i]==1) {
//                res += arr[i];
//            } else {
//                if(i+1<N) res += arr[i] * arr[++i];
//                else res += arr[i];
//            }
//        }
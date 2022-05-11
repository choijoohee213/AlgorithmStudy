package com.younghwani.a220322;

import java.io.*;
import java.util.*;
/*
일단 0을 찾는다. 0이 없으면 30의 배수가 아니다.
0을 제외한 남은 수를 내림차순. 10의 배수는 고려했으니, 남은 수를 기준으로 3의 배수를 고려하면 될 것 같다.
 */
public class Main_bj_10610_30 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuffer sf = new StringBuffer();

        char[] arr = br.readLine().toCharArray();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) sf.append(arr[i]);
        arr = sf.reverse().toString().toCharArray();

        int cnt0 = 0, tmp = 0;
        for (int i = 0; i < arr.length; i++) {
            int n = arr[i]-'0';
            if(n==0) cnt0++;
            else {
                tmp += n;
                sb.append(n);
            }
        }

        if(cnt0==0 || tmp%3!=0) System.out.println(-1);
        else {
            for (int i = 0; i < cnt0; i++) sb.append(0);
            System.out.print(sb.toString());
        }

        br.close();
    }
}

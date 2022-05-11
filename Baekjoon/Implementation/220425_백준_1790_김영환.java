package com.younghwani.a220425;

import java.io.*;
import java.util.*;

/*
BF로는 안 된다...
 */
public class Main_bj_1790_수이어쓰기2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 1 ~ N
        long K = Long.parseLong(st.nextToken()); // K번째 자리

        long res=0, digit=1, cnt=9; // 결과값, 자리수(10의digit-1승자리), 자리수 당 숫자 개수

        // K가 속하는 범위 탐색
        while(K>digit*cnt) { // 구하고자 하는 자리가 그 자리수의 총 숫자 개수보다 큰 경우, 다음 자리수로 넘어감.
            K-=digit*cnt;
            res+=cnt;
            digit+=1; // 자리수 증가
            cnt*=10; // 자리수 당 개수 증가 1~9 -> 10~99 -> 100~999 -> ...
        }

        // K 자리에 있는 숫자 확인
        res += 1; // 범위 탐색에서 더한 현재까지의 자리수에 1을 더해줘 자리수를 맞춰준다.
        res += (K-1)/digit; // 잔여 자리수

        if(res>N) System.out.print(-1); // 구한 숫자가 N보다 클 순 없음.
        else System.out.print(String.valueOf(res).charAt((int) ((K-1)%digit))); // 구한 숫자에서 알맞는 자리 출력
        br.close();
    }
}

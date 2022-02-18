package com.younghwani.a220215;

import java.io.*;
import java.util.stream.Stream;

/*
* 별 -> 동그라미 -> 네모 -> 세모 -> 무승부
* 4    3         2      1
* 입력 : 게임수(N), 플레이어(A, B)의 카드정보
* 출력 : A or B(승자) or D(무승부)
* */
public class Main_bj_14696_딱지놀이 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        loop:for (int i = 0; i < N; i++) {
            int[] player1 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] player2 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] p1 = new int[5], p2 = new int[5]; // 모양의 개수를 담음
            for (int j = 1; j <= player1[0]; j++) p1[player1[j]]++;
            for (int j = 1; j <= player2[0]; j++) p2[player2[j]]++;
            for (int j = 4; j >= 1; j--) {
                if (p1[j]>p2[j]) {System.out.println("A"); continue loop;}
                else if (p1[j]<p2[j]) {System.out.println("B"); continue loop;}
            }
            System.out.println("D");
        }
        br.close();
    }
}

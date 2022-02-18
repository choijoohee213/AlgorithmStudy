package com.younghwani.a220215;

import java.io.*;
import java.util.stream.Stream;

public class Main_bj_10163_색종이 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] pan = new int[1001][1001];
        for (int i = 1; i <= N; i++) {
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int r = arr[1]; r < arr[1]+arr[3]; r++) {
                for (int c = arr[0]; c < arr[0]+arr[2]; c++) {
                    pan[r][c] = i;
                }
            }
        }
        int[] cnt = new int[N+1];
        for (int i = 0; i < pan.length; i++) {
            for (int j = 0; j < pan[0].length; j++) {
                cnt[pan[i][j]]++;
            }
        }
        for (int i = 1; i <= N; i++) System.out.println(cnt[i]);
        br.close();
    }
}

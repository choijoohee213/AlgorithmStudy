package com.younghwani.a220215;

import java.io.*;
import java.util.stream.Stream;

public class Main_bj_2669_직사각형네개의합집합의넓이구하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] pan = new boolean[101][101];
        for (int i = 0; i < 4; i++) {
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int h = arr[0]; h < arr[2]; h++) {
                for (int k = arr[1]; k < arr[3]; k++) {
                    pan[h][k] = true; // 사각형이 있는 부분 true
                }
            }
        }
        int cnt = 0;
        for(boolean[] ba:pan) for(boolean b:ba) if(b) cnt++;
        System.out.println(cnt);
        br.close();
    }
}

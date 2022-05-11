package com.younghwani.a220210;

import java.io.*;
import java.util.stream.Stream;
/*
도화지에 놓인 색종이의 영역 구하기
색종이의 길이는 너비, 높이 모두 100인 정사각형 모양
 */
public class Main_bj_2563_색종이 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2563.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] pan = new boolean[100][100];
        int N = Integer.parseInt(br.readLine()); // 색종이 수
        int sum = 0;
        // 위치 입력받아서 도화지에 해당하는 부분 true로 찍고, 중복되지 않는 부분 sum++ 한다.
        for (int i = 0; i < N; i++) {
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int h = 0; h < 10; h++) {
                for (int k = 0; k < 10; k++) {
                    if(!pan[arr[0]+h][arr[1]+k]) { pan[arr[0]+h][arr[1]+k] = true; sum++; }
                }
            }
        }
        System.out.println(sum);
        br.close();
    }
}

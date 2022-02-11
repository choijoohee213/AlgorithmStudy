package com.younghwani.a220207;

import java.util.*;
import java.io.*;

public class Main_bj_2304_창고다각형 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2304.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        Arrays.sort(arr, Comparator.comparingInt(o1 -> o1[0]));
        int[][] arr2 = Arrays.copyOf(arr,N);
        Arrays.sort(arr2, Comparator.comparingInt(o1 -> o1[1]));
        int maxY = arr2[N-1][1]; // y의 최대값
        int result = maxY;
        int prevX = arr[0][0];
        int height = arr[0][1];
        int prevIdx = 0;
        for (int i = 1; i < N; i++) {           // 최대 y값이 되기 전까지의 범위 합
            if (arr[i][1]>=height) {
                result += (arr[i][0]-prevX) * height;
                prevX = arr[i][0];
                height = arr[i][1];
                prevIdx = i;
            }
        }
        prevX = arr[N-1][0];
        height = arr[N-1][1];
        for (int i = N-2; i >= prevIdx; i--) {        // 최대 y값 이후의 범위 합
            if (arr[i][1]>=height) {
                result += (prevX-arr[i][0]) * height;
                prevX = arr[i][0];
                height = arr[i][1];
            }
        }
        System.out.println(result);
        sc.close();
    }
}

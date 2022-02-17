package com.younghwani.a220212;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
/*
첫번째 학생은 일단 제일 앞으로.
이후 줄 선 학생은 뽑은 번호 표 만큼 앞으로 간다.
 */
public class Main_bj_2605_줄세우기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2605.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < N; i++) res.add(i-arr[i], i+1); // 뽑은 숫자만큼 인덱스 이동 후 추가
        res.forEach(i -> System.out.print(i + " "));
        br.close();
    }
}

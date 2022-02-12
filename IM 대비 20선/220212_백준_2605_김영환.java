package com.younghwani.a220212;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main_bj_2605_줄세우기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2605.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < N; i++) res.add(i-arr[i], i+1);
        res.forEach(i -> System.out.print(i + " "));
        br.close();
    }
}

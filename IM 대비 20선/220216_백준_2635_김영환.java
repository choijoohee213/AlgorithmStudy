package com.younghwani.a220213;

import java.io.*;
import java.util.*;

public class Main_bj_2635_수이어가기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2635.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            List<Integer> arr = new ArrayList<>();
            arr.add(N); arr.add(i);
            int idx = 2;
            while (arr.get(idx-2)-arr.get(idx-1) >= 0) {
                arr.add(idx, arr.get(idx-2)-arr.get(idx-1));
                idx++;
            }
            if (res.size() < arr.size()) res=arr;
        }
        System.out.println(res.size());
        res.forEach(i-> System.out.print(i + " "));
        br.close();
    }
}

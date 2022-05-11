package com.younghwani.a220213;

import java.io.*;
import java.util.*;
/*
입력받은 양의 정수를 첫 수로 하고,
임의의 다음 수를 지정한다.
그 다음 수들은 전전의 수 - 전의 수 를 통해 구한다.
위 식의 결과가 음이 될 때까지 반복한다.
 */
public class Main_bj_2635_수이어가기 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2635.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            List<Integer> arr = new ArrayList<>();
            arr.add(N); arr.add(i); // 입력 받은 수 N과 임의의 수 i를 리스트에 추가
            int idx = 2;
            while (arr.get(idx-2)-arr.get(idx-1) >= 0) { // 다음수가 음일 때까지 반복해 리스트에 값 더하기
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

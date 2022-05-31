package com.younghwani.a220531;

import java.io.*;
import java.util.*;

public class Solution_pro_입국심사 {
    public static void main(String[] args) throws Exception {
        System.out.println(solution(6, new int[]{7, 10}));
    }

    public static long solution(int n, int[] times) {
        long answer = 0; // 총 시간

        Arrays.sort(times); // 이분탐색 위한 정렬

        long s=0, m=0, e=(long) n * times[times.length-1], passenger; // 시작, 중간, 끝 시간 인덱스 및 심사승객 수

        while (s <= e) {
            m = (s + e) / 2;
            passenger = 0;
            for (int i = 0; i < times.length; i++) { // m 시간동안 처리할 수 있는 심사승객의 수 (누적합)
                passenger += m / times[i];
            }

            if (n > passenger) { // 추가 시간 필요
                s = m + 1;
            } else { // 시간 축소 필요
                e = m - 1;
                answer = m; // 큰 값에서 만족하는 작은 값 순으로
            }
        }

        return answer;
    }
}

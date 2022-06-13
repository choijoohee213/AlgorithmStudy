package com.younghwani.a220613;

import java.util.Arrays;

public class Solution_pro_단속카메라 {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}}));
    }
    public static int solution(int[][] routes) {
        int answer = 0;

//        System.out.println(Arrays.deepToString(routes));

        Arrays.sort(routes, ((o1, o2) -> o1[1] - o2[1]));

        int e = routes[0][1]; // 끝나는 지점 가장 이른 부분
        answer++;

        for (int i = 1; i < routes.length; i++) {
            if (e < routes[i][0]) { // 끝이 다른 지점 시작보다 작으면 카메라 추가 필요
                answer++;
                e = routes[i][1];
            }
        }

        return answer;
    }
}

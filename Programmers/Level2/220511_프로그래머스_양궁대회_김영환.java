package com.younghwani.a220511;

import java.util.*;
/*
라이언에게 패널티를 부여한다.
 */
public class Solution_pro_lv2_양궁대회 {
    static ArrayList<int[]> tmp = new ArrayList<>();
    static int N, apeach[], ryan[], max=Integer.MIN_VALUE;
    public static int[] solution(int n, int[] info) {
        int[] answer = {};
        // 어피치 배열 주어지고, 라이언 화살 개수인 n도 주어진다.
        // 이미 정해진 어피치 배열과 n을 적절히 나눈 배열을 비교해 점수를 구성한다.
        N = n; ryan = new int[11]; apeach = info; // 전역 변수로 사용하기 위한 작업
        dfs(0, 0);
        if(tmp.isEmpty()) answer = new int[]{-1}; // 라이언이 어피치 이기는 경우 없을 때
        else {
//            for(int[] t: tmp) {
//                System.out.println(Arrays.toString(t));
//            }
            // 가장 큰 점수 차이로 이기는 경우 중에서 가장 낮은 점수를 가장 많이 맞힌 경우 찾기
            Collections.sort(tmp, (o1, o2) -> {
                for(int i=10; i>=0; i--) {
                    if(o1[i]!=o2[i]) return Integer.compare(o2[i], o1[i]);
                }
                return 0;
            });
            answer = tmp.get(0);
        }
        return answer;
    }
    public static void dfs(int depth, int cnt) {
        if(depth==N) {
            int ap=0, ry=0;
            for(int i=0; i<=10; i++) {
                if(apeach[i]==0&&ryan[i]==0) continue;
                int K=10-i; // 현재 포커스된 과녁 점수
                if(apeach[i]<ryan[i]) ry+=K;
                else ap+=K;
            }
            if(ry>ap) {
                if(ry-ap > max) { // 점수 차가 최대인 값
                    max = ry-ap;
                    tmp.clear(); // 최대치가 갱신되면 기존 배열들은 삭제
                    tmp.add(ryan.clone());
                } else if (ry-ap == max) { // 점수 차 최대치가 여러 개인 경우 고려
                    tmp.add(ryan.clone());
                }
            }
            return;
        }
        for(int i=cnt; i<11; i++) {
            ryan[i]++;
            dfs(depth+1, i);
            ryan[i]--;
        }
    }

    public static void main(String[] args) {
        int[] ans = solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0}); // [0,2,2,0,1,0,0,0,0,0,0]
        // int[] ans = solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3}); // [1,1,1,1,1,1,1,1,0,0,2]
        System.out.print(Arrays.toString(ans));
    }
}

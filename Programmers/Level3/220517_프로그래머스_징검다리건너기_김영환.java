package com.younghwani.a220517;
/*
징검다리를 건넌다.
한번 밟을 때마다 -1
한 번에 최대 K만큼 이동 가능하다.
최대 몇 명까지 이동 가능한가?
 */
public class Solution_pro_징검다리건너기 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3)); // 3
    }
    // 처음 시도했던 방법을 이용해 이분 탐색을 적용한다.
    // 이전 방법들에선 징검다리 돌 개수에 초점을 맞췄는데, 건널 수 있는 사람 수에 초점을 맞추니 풀림.
    public static int solution(int[] stones, int k) {
        int answer = 0;

        int min=1, max=200_000_000;

        loop:while (min<=max) {
            int cnt=0;
            int mid = (min + max) / 2;

            for (int i = 0; i < stones.length; i++) {
                if (stones[i] - mid < 0) {
                    cnt++;
                } else {
                    cnt = 0;
                }

                if(cnt>=k) {
                    max = mid - 1;
                    continue loop;
                }
            }

            min = mid + 1;
            answer = Math.max(answer, mid);
        }

        return answer;
    }

    // k개씩 부분별 최대를 가지고, 전체의 최소를 구하면 된다? 될 듯 -> 시간 초과
//    public static int solution(int[] stones, int k) {
//        int answer = Integer.MAX_VALUE;
//
//        for (int i = 0; i <= stones.length-k; i++) {
//            int max = Integer.MIN_VALUE;
//            for (int j = 0; j < k; j++) {
//                max = Math.max(max, stones[i + j]);
//            }
//            if (answer > max) {
//                answer = max;
//            }
//        }
//
//        return answer;
//    }

    // 정확성은 통과하는데, 효율성은 통과 못 한다. 개선이 필요하다.
//    public static int solution_temp(int[] stones, int k) {
//        int answer = 0;
//        loop:while (true) {
//            int cnt=0;
//
//            for (int i = 0; i < stones.length; i++) {
//                if(stones[i]<=0) cnt++;
//                else {
//                    stones[i]--;
//                    cnt=0;
//                }
//
//                if(cnt>=k) {
//                    break loop;
//                }
//            }
//
//            answer++;
//        }
//
//        return answer;
//    }
}

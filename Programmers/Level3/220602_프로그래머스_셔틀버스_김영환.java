package com.younghwani.a220602;

import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_pro_셔틀버스 {
    public static void main(String[] args) {
        System.out.println(solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"})); // 09:00
        System.out.println(solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"})); // 09:09
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        StringTokenizer st;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (String time : timetable) {
//            System.out.println(time);
            st = new StringTokenizer(time, ":");
            pq.offer(60 * Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));
        }

//        System.out.println(pq.toString());

        int s=540, e=0, crews=0;
        for (int i = 0; i < n; i++) {
            crews = 0;
            while (!pq.isEmpty()) {
                if (pq.peek() > s || crews >= m) break; // 승객이 더 이상 못 타는 경우
                crews++;
                e = pq.poll() - 1;
            }
            s += t;
        }
        if (crews < m) e = s - t;

//        System.out.println(e);

//        System.out.printf("%02d:%02d", e/60, e%60);

        StringBuilder sb = new StringBuilder();
        answer = sb.append(String.format("%02d", e / 60)).append(":").append(String.format("%02d", e % 60)).toString();
        return answer;
    }
}

package com.younghwani.a220602;

import java.util.stream.Stream;

public class Solution_pro_추석트래픽 {
    public static void main(String[] args) {
//        System.out.println(solution(new String[]{"2016-09-15 00:00:00.000 3s"})); // 1
//        System.out.println(solution(new String[]{"2016-09-15 23:59:59.999 0.001s"})); // 1
//        System.out.println(solution(new String[]{"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"})); // 1
        System.out.println(solution(new String[]{"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"})); // 2
//        System.out.println(solution(new String[]{"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
//                "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
//                "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
//                "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"})); // 7
//        System.out.println(solution(new String[]{"2016-09-15 00:00:00.000 2.3s", "2016-09-15 23:59:59.999 0.1s"})); // 1
    }

    public static int solution(String[] lines) {
        int answer = 0;

        int idx = 0;
        String[] logs = new String[lines.length];
        for (String line : lines) {
            String[] tmp = line.split(" ");
            double[] time = Stream.of(tmp[1].split(":")).mapToDouble(Double::parseDouble).toArray();
            logs[idx++] = (time[0]*3600+time[1]*60+time[2]) + " " + tmp[2].replace("s", "");
        }

        int[] T = new int[logs.length]; // 시간 영역 별 로그 처리량

        for (int i = 0; i < logs.length; i++) {
            String[] tmp = logs[i].split(" ");
            double end = Double.parseDouble(tmp[0]);
            for (int j = i; j < logs.length; j++) {
                tmp = logs[j].split(" ");
                double start = Double.parseDouble(tmp[0]) - Double.parseDouble(tmp[1]) + 0.001;
                if (start < end + 1) {
                    T[i]++;
                }
            }
        }

        for (int t : T) answer = Math.max(answer, t);

        return answer;
    }
}

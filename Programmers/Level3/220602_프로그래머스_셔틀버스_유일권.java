import java.util.*;

class Shuttle {
    public static void main(String[] args) {
        System.out.println(solution(1,1,5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(solution(2,10,2, new String[]{"09:10", "09:09", "08:00"}));
        System.out.println(solution(2,1,2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        System.out.println(solution(1,1,5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));
        System.out.println(solution(1,1,1, new String[]{"23:59"}));
        System.out.println(solution(10,60,45, new String[]{"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }
    public static String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(String table : timetable) {
            int time = Integer.parseInt(table.substring(0, 2)) * 60 + Integer.parseInt(table.substring(3));
            pq.add(time);
        }

        int start_time = 9 * 60;
        int last_time = 0;
        int total = 0;
        for(int i = 0; i < n; i++) {
            total = 0;                                  //탄 인원수
            while(!pq.isEmpty()) {
                int current_time = pq.peek();
                if(current_time <= start_time && total < m) {
                    pq.poll();
                    total++;
                } else break;
                last_time = current_time - 1;
            }
            start_time += t;
        }
        if(total < m) last_time = start_time - t;       //마지막에 셔틀이 꽉 안찼을 경우

        String hour = String.format("%02d", last_time / 60);
        String minute = String.format("%02d", last_time % 60);
        return hour + ":" + minute;
    }
}

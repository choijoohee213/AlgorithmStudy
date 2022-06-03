import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"}));
        System.out.println(solution(new String[]{"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"}));
        System.out.println(solution(new String[]{
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        }));
    }
    public static int solution(String[] lines) {
        int answer=0;
        List<Integer> checkPoint = new ArrayList<>();
        for(String line : lines) {
            int[] section = timeToMilliSec(line);
            checkPoint.add(section[0]);
            checkPoint.add(section[1]);
        }
        Collections.sort(checkPoint);

        for(int s : checkPoint){
            int e = s + 999;

            int cnt=0;
            for(String line : lines) {
                int[] log = timeToMilliSec(line);

                if(e < log[0] || log[1] < s ) continue;
                cnt++;
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    static int[] timeToMilliSec(String line) {          //각 로그의 시작, 끝점 ms로 변경
        line = line.substring(11, line.length()-1);
        String[] data = line.split(" ");
        String[] time = data[0].split(":");
        double space = Double.parseDouble(data[1])*1000;

        int hhToMS = Integer.parseInt(time[0])*60*60*1000;
        int mmToMS = Integer.parseInt(time[1])*60*1000;
        double ssToMS = Double.parseDouble(time[2])*1000;

        int endTimeToMS = hhToMS + mmToMS + (int)ssToMS;
        int startTimeToMS = endTimeToMS - (int)space+1;

        return new int[] {startTimeToMS, endTimeToMS};
    }
}
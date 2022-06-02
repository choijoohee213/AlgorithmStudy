import java.util.*;

class Solution {
	public String solution(int n, int t, int m, String[] timetable) {
		int hour = 9, minute = 0, idx = 0;
		int[] bus = new int[n];
		int[] maxTime = new int[n];
		Arrays.sort(timetable);

		for (int i = 0; i < n; i++) {
			int cnt = 0;

			for (; idx< timetable.length; idx++) {
				if(timetable[idx].equals("23:59")) continue;
				String[] time = timetable[idx].split(":");
				int H = Integer.parseInt(time[0]);
				int M = Integer.parseInt(time[1]);
				if(H < hour || (H == hour && M <= minute)) {
					cnt++;
					maxTime[i] = Math.max(maxTime[i], H*60+M);
					if(cnt == m) {
						idx++;
						break;
					}
				} else break;
			}
			bus[i] = cnt;
			minute += t;
			if(minute >= 60) {
				hour++;
				minute -= 60;
			}
		}
		if(bus[n-1] == m) {
			minute = maxTime[n-1] - 1;
			hour = minute/60;
			minute %= 60;
		} else if(bus[n-1] < m) {
			minute = t * (n-1);
			hour = 9 + minute/60;
			minute %= 60;
		}

		StringBuilder sb = new StringBuilder();
		if(hour < 10) sb.append('0');
		sb.append(hour).append(':');
		if(minute<10) sb.append('0');
		sb.append(minute);
		return sb.toString();
	}
}
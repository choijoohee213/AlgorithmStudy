import java.util.*;

class Solution {
	public int solution(String[] lines)  {
		int answer = 0;
		List<long[]> list = new ArrayList<>();

		for (String line : lines) {
			String[] log = line.split(" ");
			String[] time = log[1].split(":");
			long processingTime = (int)(Double.parseDouble(log[2].substring(0, log[2].length() - 1)) * 1000);
			long startTime, endTime = 0;

			endTime += Long.parseLong(time[0]) * 60 * 60 * 1000;
			endTime += Long.parseLong(time[1]) * 60 * 1000;
			endTime += (int)(Double.parseDouble(time[2]) * 1000);
			startTime = endTime - processingTime + 1;

			list.add(new long[] {startTime, endTime});
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0] + " " + list.get(i)[1]);
			long endTime = list.get(i)[1];
			int cnt = 0;
			for (int j = i; j < list.size(); j++) {
				if(endTime + 1000 >= list.get(j)[0]) {
					cnt++;
				}
			}
			answer = Math.max(cnt, answer);
		}
		return answer;
	}
}
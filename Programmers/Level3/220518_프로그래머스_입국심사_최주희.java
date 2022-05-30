import java.util.Arrays;

public class 입국심사 {
	public long solution(int n, int[] times) {
		Arrays.sort(times);
		long answer = 0, start = 1, end = (long)n * times[times.length-1], mid;

		while(start <= end) {
			mid = (start + end) / 2;
			long sum = 0;

			for (int time : times) {
				sum += mid / time;  //사람 수 = 주어진 시간 / 해결 시간
			}
			if(sum >= n) {  //시간을 더 줄일 수 있음
				end = mid - 1;
				answer = mid;
			} else {
				start = mid + 1;
			}
		}
		return answer;
	}
}

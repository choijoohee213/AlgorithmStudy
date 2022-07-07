import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		long min = 0, max = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max += arr[i];
			min = Math.max(min, arr[i]); //블루레이를 모두 담을 수는 있어야해서 가장 큰값으로
		}

		while(min <= max) {
			long mid = (min + max) / 2;
			long sum = 0;
			int cnt = 0;

			for (int num : arr) {
				if(sum + num > mid) {
					sum = 0;
					cnt++;
				}
				sum += num;
			}
			if(sum != 0) cnt++;
			if(cnt > m) {
				min = mid + 1;
			} else {
				max = mid -1;
			}
		}
		br.close();
		System.out.print(min);
	}
}
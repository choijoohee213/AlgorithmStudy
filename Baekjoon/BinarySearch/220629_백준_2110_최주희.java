import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		if(c == 2) {
			System.out.print(arr[n-1] - arr[0]);
			return;
		}

		int min = 1, max = arr[n-1] - arr[0], result = 0;
		while(min <= max) {
			int mid = (min + max) / 2;
			int cnt = 1, prev = arr[0];
			for (int i = 1; i < n; i++) {
				if(arr[i] - prev >= mid) {
					prev = arr[i];
					cnt++;
				}
			}
			if(cnt >= c) {
				result = mid;
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		br.close();
		System.out.print(result);
	}
}
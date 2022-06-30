import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[k];
		long min = 1, max = 1;
		for (int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(arr[i], max);
		}

		while(min <= max) {
			long mid = (max + min) / 2;
			long sum = 0;
			for (int line : arr) {
				if(line < mid) continue;
				sum += line / mid;
			}
			if(sum >= n) {
				//길이를 늘리면 sum -
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		br.close();
		System.out.print(max);
	}
}
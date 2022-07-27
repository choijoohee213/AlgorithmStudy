import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer. parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			int start = 0, end = n-1;
			while(true) {
				if(start == i) start++;
				if(end == i) end--;
				if(start >= end) break;

				int sum = arr[start] + arr[end];
				if(sum == arr[i]) {
					cnt++;
					break;
				}
				if(sum > arr[i]) {
					end--;
				} else {
					start++;
				}
			}
		}
		br.close();
		System.out.print(cnt);
	}
}
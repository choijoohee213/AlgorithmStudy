import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int x = Integer.parseInt(st.nextToken());
			int start = 0, end = n-1, result = 0;
			while (start <= end) {
				int mid = (start + end) / 2;
				if (arr[mid] > x) {
					end = mid - 1;
				} else if (arr[mid] < x) {
					start = mid + 1;
				} else {
					result = 1;
					break;
				}
			}
			sb.append(result).append('\n');
		}
		br.close();
		System.out.print(sb);
	}
}
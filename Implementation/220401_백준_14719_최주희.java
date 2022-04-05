import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int result = 0, left = 0, right = 0;
		int[] arr = new int[w];
		for (int j = 0; j < w; j++) {
			arr[j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < w - 1; i++) {
			left = right = 0;
			// 왼쪽에서 max 높이
			for (int j = 0; j < i; j++) {
				left = Math.max(arr[j], left);
			}
			// 오른쪽에서 max 높이
			for (int j = i + 1; j < w; j++) {
				right = Math.max(arr[j], right);
			}

			if (arr[i] < left && arr[i] < right) {
				// 더 낮은 것을 기준
				result += Math.min(left, right) - arr[i];
			}
		}
		br.close();
		System.out.print(result);
	}
}
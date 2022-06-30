import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int start = 0, end = 0;
		int[] tree = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, tree[i]);
		}

		while(start <= end) {
 			int mid = (start + end) / 2;
			long sum = 0;

			for (int t : tree) {
				if((t - mid) < 0) continue;
				sum += t - mid;
			}
			if(sum >= m) {
				start = mid + 1;  //높이를 높임
			} else {
				end = mid - 1;
			}
		}
		br.close();
		System.out.print(end);
	}
}
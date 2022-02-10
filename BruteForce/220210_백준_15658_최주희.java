import java.io.*;
import java.util.*;

class Main {
	static int n, result_max = Integer.MIN_VALUE, result_min = Integer.MAX_VALUE;
	static int[] arr;
	static int[] op = new int[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		permutation(0, arr[0]);
		System.out.println(result_max);
		System.out.print(result_min);
		br.close();
	}

	static void permutation(int cnt, int sum) {
		if(cnt == n-1) {
			result_max = Math.max(sum, result_max);
			result_min = Math.min(sum, result_min);
			return;
		}

		for(int i=0; i<4; i++) {
			if(op[i]<=0) continue;
			int s = sum;
			if(i == 0) s += arr[cnt+1];
			else if(i == 1) s -= arr[cnt+1];
			else if(i == 2) s *= arr[cnt+1];
			else if(i == 3) s /= arr[cnt+1];
			op[i]--;
			permutation(cnt+1, s);
			op[i]++;
		}
	}
}
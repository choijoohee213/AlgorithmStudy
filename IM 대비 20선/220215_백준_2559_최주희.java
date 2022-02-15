import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int result = Integer.MIN_VALUE;
		for(int i=0; i<=n-k; i++) {
			int sum = 0;
			for(int j=i; j<i+k; j++) {
				sum += arr[j];
			}
			result = Math.max(sum, result);
		}
		System.out.print(result);
	}
}
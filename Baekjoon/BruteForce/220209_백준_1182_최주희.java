import java.io.*;
import java.util.*;

class Main {
	static int n, s, cnt = 0;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n];

		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		permutation(0, 0);
		System.out.println(cnt);
	}

	static void permutation(int idx, int sum) {
		if(idx != 0 && sum == s) cnt++;
		for(int i=idx; i<n; i++) {
			permutation(i+1, sum + arr[i]);
		}
	}
}
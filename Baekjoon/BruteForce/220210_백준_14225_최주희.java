import java.util.*;
import java.io.*;

class Main {
	static int n;
	static int[] arr;
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		permutation(0, 0);
		int num = 1;
		while(set.contains(num++)) {}
		System.out.print(num-1);
	}

	static void permutation(int cnt, int sum) {
		if(cnt == n){
			set.add(sum);
			return;
		}
		permutation(cnt+1, sum+arr[cnt]);
		permutation(cnt+1, sum);
	}
}
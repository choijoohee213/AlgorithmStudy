import java.io.*;
import java.util.*;

class Main {
	static int n, result_max = Integer.MIN_VALUE, result_min = Integer.MAX_VALUE;
	static int[] arr;
	static char[] op;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		op = new char[n-1];
		visited = new boolean[n-1];
		HashMap<Integer, Character> opHashMap = new HashMap<Integer,Character>(){{
			put(0, '+');
			put(1, '-');
			put(2, '*');
			put(3, '/');
		}};

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int idx = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<4; i++) {
			int x = Integer.parseInt(st.nextToken());
			for(int j=0; j<x; j++) {
				op[idx++] = opHashMap.get(i);
			}
		}
		permutation(0, "");
		System.out.println(result_max);
		System.out.println(result_min);
	}

	static void permutation(int cnt, String str) {
		if(cnt == n-1) {
			calculate(str);
			return;
		}

		for(int i=0; i<n-1; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			permutation(cnt+1, str+op[i]);
			visited[i] = false;
		}
	}

	static void calculate(String str) {
		int sum = arr[0];
		for(int i=1; i<n; i++) {
			if(str.charAt(i-1) == '+') sum += arr[i];
			else if(str.charAt(i-1) == '-') sum -= arr[i];
			else if(str.charAt(i-1) == '*') sum *= arr[i];
			else if(str.charAt(i-1) == '/') sum /= arr[i];
		}

		result_max = Math.max(sum, result_max);
		result_min = Math.min(sum, result_min);
	}
}
import java.io.*;
import java.util.*;

class Main {
	static String[] arr;
	static int k;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			k = Integer.parseInt(st.nextToken());
			if(k == 0) break;
			arr = new String[k];
			for(int i=0; i<k; i++) {
				arr[i] = st.nextToken();
			}
			permutation(0, 0, "");
			sb.append('\n');
		}
		System.out.print(sb);
	}

	static void permutation(int cnt, int idx, String str) {
		if(cnt == 6) {
			sb.append(str).append('\n');
			return;
		}
		for(int i=idx; i<k; i++) {
			permutation(cnt+1, i+1, str+arr[i]+" ");
		}
	}
}
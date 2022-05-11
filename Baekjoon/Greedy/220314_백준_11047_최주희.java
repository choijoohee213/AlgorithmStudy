import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int cnt = 0, idx = n-1;
		while(k>0) {
			if(arr[idx] <= k) {
				cnt += k / arr[idx];
				k %= arr[idx];
			}
			idx = idx==0? n-1 : idx-1;
		}
		br.close();
		System.out.print(cnt);
	}
}
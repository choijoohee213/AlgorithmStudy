import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), result = 1, cnt1 = 1, cnt2 = 1;
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i==0) continue;
			if(arr[i-1] <= arr[i]) {
				cnt1++;
			} else {
				result = Math.max(result, cnt1);
				cnt1 = 1;
			}

			if(arr[i-1] >= arr[i]) {
				cnt2++;
			} else {
				result = Math.max(result, cnt2);
				cnt2 = 1;
			}
		}
		result = Math.max(result, cnt1);
		result = Math.max(result, cnt2);
		System.out.print(result);
	}
}
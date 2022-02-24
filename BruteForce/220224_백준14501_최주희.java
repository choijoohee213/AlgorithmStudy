import java.io.*;
import java.util.*;

class Main {
	static int n, result = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n+1][2]; //t,p

		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		getMaxRevenue(1, 0, arr);
		br.close();
		System.out.print(result);
	}

	static void getMaxRevenue(int idx, int sum, int[][] arr) {
		result = Math.max(result, sum);
		for(int i=idx; i<=n; i++) {
			int nx = i + arr[i][0];
			if(nx <= n+1) getMaxRevenue(nx, sum + arr[i][1], arr);
		}
	}
}
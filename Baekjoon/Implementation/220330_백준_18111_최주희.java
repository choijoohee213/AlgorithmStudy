import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		int maxValue = 0, minValue = Integer.MAX_VALUE;
		int resultTime = Integer.MAX_VALUE, resultHeight = 0;

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxValue = Math.max(arr[i][j], maxValue);
				minValue = Math.min(arr[i][j], minValue);
			}
		}

		for(int i=minValue; i<=maxValue; i++) {
			int t = 0, inventory = b;

			for(int j=0; j<n; j++) {
				for(int k=0; k<m; k++) {
					int diff = arr[j][k] - i;
					if(diff < 0) {  //블록 채우기
						inventory -= Math.abs(diff);
						t += Math.abs(diff);
					} else {  //블록 제거
						inventory += Math.abs(diff);
						t += Math.abs(diff) * 2;
					}
				}
			}
			if(inventory < 0) continue;  //블록이 모자라서 안됨
			if(resultTime >= t) {
				resultTime = t;
				resultHeight = i;
			}
		}
		System.out.print(resultTime + " " + resultHeight);
	}
}
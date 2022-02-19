import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] arr = new int[2][6];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())-1]++;
		}
		int cnt = 0;
		for(int i=0; i<6; i++){
			if(arr[0][i] % k != 0) cnt++;
			if(arr[1][i] % k != 0) cnt++;
			cnt += arr[0][i] / k;
			cnt += arr[1][i] / k;
		}
		br.close();
		System.out.print(cnt);
	}
}
import java.io.*;
import java.util.*;

class Main {
	static int[] result = new int[7];
	static int[] arr = new int[9];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i=0; i<9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		cal(0, 0, 0);
		Arrays.sort(result);
		for(int i=0; i<7; i++) {
			System.out.println(result[i]);
		}
	}

	static boolean cal(int cnt, int idx, int sum) {
		if(cnt == 7 && sum == 100) {
			return true;
		}
		for(int i=idx; i<9; i++) {
			if(cal(cnt + 1,i+1, sum + arr[i])) {
				result[cnt] = arr[i];
				return true;
			}
		}
		return false;
	}
}
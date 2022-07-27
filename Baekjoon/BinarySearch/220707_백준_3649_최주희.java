import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			//구멍의 너비는 x 센티미터이고, 구멍에 넣을 두 조각의 길이의 합은 구멍의 너비와 정확하게 일치해야 한다
			String str = br.readLine();
			if (str == null || str.equals(""))
				break;
			double x = Double.parseDouble(str);
			int n = Integer.parseInt(br.readLine());
			double[] arr = new double[n];
			for (int i = 0; i < n; i++) {
				double l = Double.parseDouble(br.readLine());
				arr[i] = l;
			}

			Arrays.sort(arr);
			int start = 0, end = arr.length - 1;
			double l1 = 0, l2 = 0;
			boolean flag = false;

			while (start < end) {
				double sum = (arr[start] + arr[end]) / 10000000;
				if (sum == x) {
					l1 = arr[start];
					l2 = arr[end];
					flag = true;
					break;
				}
				if (sum > x) {
					end--;
				} else {
					start++;
				}
			}
			if (flag)
				System.out.printf("yes %d %d\n", (int)l1, (int)l2);
			else
				System.out.print("danger\n");
		}
		br.close();
	}
}
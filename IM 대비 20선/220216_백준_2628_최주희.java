import java.util.*;
import java.io.*;

public class 종이자르기_baekjoon2628 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(br.readLine());
		List<Integer> arr_r = new ArrayList<>(n);
		List<Integer> arr_c = new ArrayList<>(n);

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 0)
				arr_r.add(b);
			else
				arr_c.add(b);
		}
		//마지막 좌표 넣고 오름차순 정렬
		arr_r.add(c);
		arr_c.add(r);
		Collections.sort(arr_r);
		Collections.sort(arr_c);

		int x = 0, y = 0, result = 0;
		for (int a : arr_r) {
			y = 0;
			for (int b : arr_c) {
				result = Math.max(result, (a - x) * (b - y));  //이번좌표-저번좌표로 길이 구하여 계산
				y = b;
			}
			x = a;
		}
		System.out.print(result);
	}
}

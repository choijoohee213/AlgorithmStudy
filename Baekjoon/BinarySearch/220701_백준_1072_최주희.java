import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		long x = Integer.parseInt(st.nextToken());
		long y = Integer.parseInt(st.nextToken());
		long z = 100 * y / x;
		if(z >= 99) {
			System.out.print(-1);
		} else {
			long min = 1, max = x;
			while (min <= max) {
				long mid = (min + max) / 2;
				long nz = 100 * (y + mid) / (x + mid);

				if (nz > z) {
					max = mid - 1;
				} else {
					min = mid + 1;
				}
			}
			System.out.print(min);
		}
		br.close();
	}
}
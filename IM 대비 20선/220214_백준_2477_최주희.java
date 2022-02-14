import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[6];
		int k = Integer.parseInt(br.readLine()), maxX = 0, maxY = 0, xIdx = 0, yIdx = 0, dir, len;
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			dir = Integer.parseInt(st.nextToken());
			len = Integer.parseInt(st.nextToken());

			if (dir == 1 || dir == 2) {
				if (maxX < len) {
					maxX = len;
					xIdx = i;
				}
			} else if (maxY < len) {
				maxY = len;
				yIdx = i;
			}
			arr[i] = len;
		}
		int x1 = arr[5], x2 = arr[0], y1 = arr[5], y2 = arr[0];
		if (xIdx - 1 >= 0)
			y1 = arr[xIdx - 1];
		if (xIdx + 1 < 6)
			y2 = arr[xIdx + 1];
		if (yIdx - 1 >= 0)
			x1 = arr[yIdx - 1];
		if (yIdx + 1 < 6)
			x2 = arr[yIdx + 1];
		System.out.println((maxX * Math.min(y1, y2) + (Math.min(x1, x2) * (maxY - Math.min(y1, y2)))) * k);
	}
}
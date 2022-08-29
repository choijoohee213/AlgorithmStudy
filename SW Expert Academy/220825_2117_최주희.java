import java.io.*;
import java.util.*;

class Solution {
	static int n, m;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int result = 0, totalHouseCnt = 0;
			arr = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 1) {
						totalHouseCnt++;
					}
				}
			}

			int k = 0, cost, profit;
			cal : while (++k > 0) {
				cost = (k * k) + ((k - 1) * (k - 1));

				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						int cnt = checkHouseCount(k, i, j);
						profit = cnt * m - cost;
						if(profit >= 0) {
							result = Math.max(result, cnt);
						}
						if(cnt == totalHouseCnt) {
							break cal;
						}
					}
				}
			}
			sb.append("#" + tc + " " + result + "\n");
		}

		br.close();
		System.out.print(sb);
	}

	private static int checkHouseCount(int k, int x, int y) {
		int cnt = 0, cx = x + k - 1, cy = y;
		//마름모 맨아래행->중앙행
		for (int i = 0; i < k; i++) {
			int startX = cx - i, startY = cy - (i * 1);
			for (int j = 0; j < (2 * (i + 1) - 1); j++) {
				if (startX >= 0 && startX < n && (startY + j) >= 0 && (startY + j) < n
					&& arr[startX][startY + j] == 1) {
					cnt++;
				}
			}
		}

		//마름모 맨위행->중앙행-1
		cx = x - (k - 1);
		for (int i = 0; i < k - 1; i++) {
			int startX = cx + i, startY = cy - (i * 1);
			for (int j = 0; j < (2 * (i + 1) - 1); j++) {
				if (startX >= 0 && startX < n && (startY + j) >= 0 && (startY + j) < n
					&& arr[startX][startY + j] == 1) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
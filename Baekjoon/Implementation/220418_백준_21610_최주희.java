import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int[] dx = {0, -1, -1, -1, 0, 1, 1, 1}, dy = {-1, -1, 0, 1, 1, 1, 0, -1};
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		boolean[][] cloud = new boolean[n][n];
		cloud[n - 1][0] = cloud[n - 1][1] = cloud[n - 2][0] = cloud[n - 2][1] = true;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken()) % n;

			//구름 이동
			boolean[][] newCloud = new boolean[n][n];
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if (!cloud[x][y]) continue;
					int nx = x + dx[d] * s;
					int ny = y + dy[d] * s;
					if (nx < 0) nx = n + nx;
					else if (nx >= n) nx %= n;
					if (ny < 0) ny = n + ny;
					else if (ny >= n) ny %= n;

					cloud[x][y] = false;  //구름 모두 사라짐
					newCloud[nx][ny] = true;
				}
			}

			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if (!newCloud[x][y]) continue;
					arr[x][y]++;  //물의 양이 1증가
				}
			}

			//물복사 버그 마법 시전
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if (!newCloud[x][y]) continue;
					int cnt = 0;

					//대각선 거리 1인 물 있는 바구니 수 세기
					for (int j = 1; j <= 7; j += 2) {
						int nx = x + dx[j];
						int ny = y + dy[j];
						if (nx < 0 || nx >= n || ny < 0 || ny >= n || arr[nx][ny] == 0) continue;
						cnt++;
					}
					arr[x][y] += cnt;
				}
			}

			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if(arr[x][y] >= 2 && !newCloud[x][y]) {
						cloud[x][y] = true;
						arr[x][y] -= 2;
					}
				}
			}
		}

		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += arr[i][j];
			}
		}
		br.close();
		System.out.print(sum);
	}
}
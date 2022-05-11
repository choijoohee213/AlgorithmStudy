import java.io.*;
import java.util.*;

/**
 * 상하좌우 인접한 블록들은 블록그룹에 속함 (모두 연결되어있음)
 * 블록 그룹
 * 	 - 블록의 개수가 2이상
 *   - 일반 블록이 1개 이상이며 색이 모두 같아야함
 *   - 검은색 블록(-1) x
 *   - 무지개 블록(0) 상관 없음
 *   - 기준 블록 : 무지개 블록이 아니고 행의 번호, 열의 번호 (오름차순)
 *
 * 격자에 중력 작용 : 검은색 블록 제외 모든 블록이 행 증가 (다른 블록이나 경계전까지 계속)
 */

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		int sum = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while(true) {
			int idx = 1, maxGroupIdx = 0, maxGroupSize = 0, maxRainbow = 0;
			int[][] group = new int[n][n];  //맵에 속한 그룹 인덱스 표시
			List<Integer>[] list = new ArrayList[n*n+1];  //블록 그룹이 최대 n*n개
			for (int i = 1; i <= n*n; i++) {
				list[i] = new ArrayList<>();
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					//일반블록이 아니거나(일반블록이 적어도 하나있어야해서) 그룹에 속해있으면 생략
					if (arr[i][j] <= 0 || group[i][j] != 0) continue;

					int cnt = 0;  //무지개 블록 개수
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[] {i, j});
					boolean[][] visited = new boolean[n][n];
					visited[i][j] = true;
					list[idx].add(i * n + j);
					int num = arr[i][j];

					while (!q.isEmpty()) {
						int[] x = q.poll();

						for (int k = 0; k < 4; k++) {
							int nx = x[0] + dx[k];
							int ny = x[1] + dy[k];

							if (nx < 0 || nx >= n || ny < 0 || ny >= n || arr[nx][ny] <= -1  //빈칸이거나 검은색일 경우 ㅅ애략
								|| (arr[nx][ny] != 0 && arr[nx][ny] != num)  //일반 블록일 경우 같은 색이어야함
								|| visited[nx][ny] || group[i][j] != 0) {
								continue;
							}
							if(arr[nx][ny] == 0) cnt++;  //무지개 블록 개수 증가
							q.offer(new int[] {nx, ny});
							visited[nx][ny] = true;
							list[idx].add(nx * n + ny);
						}
					}

					if (list[idx].size() >= 2) {  //블록 그룹의 개수가 2이상이어야함
						for (Integer pos : list[idx]) {
							if(arr[pos/n][pos%n] != 0)  //무지개 블록은 어떤 그룹에든 들어갈 수 있음(그룹 표시안해주기)
								group[pos/n][pos % n] = idx;
						}
						//크기가 가장 큰 블록 그룹 -> 무지개 블록이 많은 그룹 -> 행,열이 큰것 순
						if (maxGroupSize < list[idx].size()) {
							maxGroupSize = list[idx].size();
							maxGroupIdx = idx;
							maxRainbow = cnt;
						} else if(maxGroupSize == list[idx].size()) {
							if(maxRainbow <= cnt) {
								maxGroupIdx = idx;
								maxRainbow = cnt;
							}
						}
					}
					idx++;
				}
			}
			if(maxGroupIdx == 0) break;  //블록 그룹이 없으면 종료

			for (Integer pos : list[maxGroupIdx]) {  //크기가 가장 큰 블록 그룹 제거
				arr[pos/n][pos%n] = -2;
			}
			sum += maxGroupSize * maxGroupSize;

			//중력 작용
			for (int i = 0; i < n; i++) {
				int j = n-1;
				for (; j >= 0; j--) {
					if(arr[j][i] >= 0) {
						int k = j+1;
						for (; k < n; k++) {
							if(arr[k][i] != -2) break;
						}
						if(k-1 != j) {
							arr[k - 1][i] = arr[j][i];
							arr[j][i] = -2;
						}
					}
				}
			}

			//90도 반시계 방향 회전
			int[][] newArr = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					newArr[i][j] = arr[j][n-i-1];
				}
			}
			arr = newArr;

			//다시 중력 작용
			for (int i = 0; i < n; i++) {
				int j = n-1;
				for (; j >= 0; j--) {
					if(arr[j][i] >= 0) {
						int k = j+1;
						for (; k < n; k++) {
							if(arr[k][i] != -2) break;
						}
						if(k-1 != j) {
							arr[k - 1][i] = arr[j][i];
							arr[j][i] = -2;
						}
					}
				}
			}
		}
		br.close();
		System.out.print(sum);
	}
}
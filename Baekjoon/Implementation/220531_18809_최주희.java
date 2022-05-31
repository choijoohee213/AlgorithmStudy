import java.io.*;
import java.util.*;

class Main {
	static int n, m, g, r, result = 0;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		redComputation(0, 0);
		br.close();
		System.out.print(result);
	}

	private static void redComputation(int cnt, int idx) {
		if(cnt == r) {
			greenComputation(0, 0);
			return;
		}
		for (int i = idx; i < n*m; i++) {
			if(map[i/m][i%m] != 2) continue;
			map[i/m][i%m] = 3;
			redComputation(cnt+1, i+1);
			map[i/m][i%m] = 2;
		}
	}

	private static void greenComputation(int cnt, int idx) {
		if(cnt == g) {
			int[][] newMap = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					newMap[i][j] = map[i][j];
				}
			}
			bfs(newMap);
			return;
		}
		for (int i = idx; i < n*m; i++) {
			if(map[i / m][i % m] != 2) continue;
			map[i/m][i%m] = 4;
			greenComputation(cnt+1, i+1);
			map[i/m][i%m] = 2;
		}
	}

	private static void bfs(int[][] newMap) {
		int cnt = 0;
		Queue<Integer> redQueue = new ArrayDeque<>();
		Queue<Integer> greenQueue = new ArrayDeque<>();
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(newMap[i][j] == 3) { //red
					redQueue.offer(i*m+j);
				} else if(newMap[i][j] == 4) { //green
					greenQueue.offer(i*m+j);
				}
			}
		}

		while(redQueue.size()>0 && greenQueue.size()>0) {
			Set<Integer> nowSpread = new HashSet<>();
			int size = redQueue.size();
			for (int i = 0; i < size; i++) {
				int pos = redQueue.poll();
				if(newMap[pos/m][pos%m] != 3) continue; //이전에 넣었던 것중 꽃이 된것은 제외

				for (int j = 0; j < 4; j++) {
					int nx = pos / m + dx[j];
					int ny = pos % m + dy[j];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m
						|| newMap[nx][ny] == 0 || newMap[nx][ny] == 3 || newMap[nx][ny] == 4)
						continue;
					redQueue.offer(nx * m + ny);
					newMap[nx][ny] = 3;
					nowSpread.add(nx * m + ny);
				}
			}

			size = greenQueue.size();
			for (int i = 0; i < size; i++) {
				int pos = greenQueue.poll();

				for (int j = 0; j < 4; j++) {
					int nx = pos / m + dx[j];
					int ny = pos % m + dy[j];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m
						|| newMap[nx][ny] == 0 || newMap[nx][ny] == 4)
						continue;
					if (newMap[nx][ny] == 3) {
						if (nowSpread.contains(nx * m + ny)) {  //꽃
							newMap[nx][ny] = 0;
							cnt++;
						}
						continue;
					}
					greenQueue.offer(nx * m + ny);
					newMap[nx][ny] = 4;
				}
			}
		}
		result = Math.max(result, cnt);
	}
}
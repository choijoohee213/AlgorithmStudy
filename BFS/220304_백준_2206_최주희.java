import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		if(n == 1 && m == 1) {  //100퍼센트에서 틀린 이유
			System.out.print(1);
			return;
		}
		char[][] arr = new char[n][m];
		int[][][] visited = new int[n][m][2];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, 0}); //x,y,벽 부셨는지 여부
		visited[0][0][0] = 1;

		while (!q.isEmpty()) {
			int[] x = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = x[0] + dx[i];
				int ny = x[1] + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny][x[2]] > 0) continue;
				if(nx == n-1 && ny == m-1) {
					System.out.print(visited[x[0]][x[1]][x[2]] + 1);
					return;
				}
				if (arr[nx][ny] == '1') {
					if(x[2] == 1) continue;
					//벽을 부순적이 없으면, 부수기
					q.offer(new int[] {nx, ny, 1});
					visited[nx][ny][1] = visited[x[0]][x[1]][x[2]] + 1;
				} else {  //벽이 아니면, 이전 상태를 유지
					q.offer(new int[] {nx, ny, x[2]});
					visited[nx][ny][x[2]] = visited[x[0]][x[1]][x[2]] + 1;
				}
			}
		}
		br.close();
		System.out.print(-1);
	}
}
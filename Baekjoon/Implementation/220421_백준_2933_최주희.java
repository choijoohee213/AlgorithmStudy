import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] arr = new char[r][c];
		for (int i = 0; i < r; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int h = Integer.parseInt(st.nextToken());

			if(i % 2 == 0) { //왼쪽에서
				int x = r-h, y = 0 ;
				for (; y < c; y++) {
					if(arr[x][y] == 'x') break;
				}
				if(y == c) continue; //미네랄이 없음
				arr[x][y] = '.';
			} else { //오른쪽에서
				int x = r-h, y = c-1;
				for (; y >= 0; y--) {
					if(arr[x][y] == 'x') break;
				}
				if(y == -1) continue;
				arr[x][y] = '.';
			}

			int[][] visited = new int[r][c];
			for (int j = 0; j < c; j++) {  //맨 아래 행에 연결되어 있는 지 여부로 땅에 떨어질지 체크
				if(arr[r-1][j] == '.') continue;

				Queue<int[]> q = new ArrayDeque<>();
				q.offer(new int[]{r-1, j});
				visited[r-1][j] = 1;

				while(!q.isEmpty()) {  //bfs
					int[] x = q.poll();
					for (int k = 0; k < 4; k++) {
						int nx = x[0] + dx[k];
						int ny = x[1] + dy[k];

						if(nx<0 || nx>=r || ny<0 || ny>=c || arr[nx][ny] == '.' || visited[nx][ny] == 1) continue;
						q.offer(new int[]{nx,ny});
						visited[nx][ny] = 1;
					}
				}
			}

			//공중에 떠있는 클러스터 찾기
			List<int[]> list = new ArrayList<>();
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					if(arr[j][k] == 'x' && visited[j][k] == 0) {
						list.add(new int[]{j,k});
						arr[j][k] = '.';
					}
				}
			}
			if(list.isEmpty()) continue;

			boolean flag = true;
			while(flag) {  //떨어질 높이 체크
				for (int[] pos : list) {
					int nx = pos[0] + 1;
					int ny = pos[1];

					if(nx<0 || nx>=r || ny<0 || ny>=c || arr[nx][ny] == 'x') {
						flag = false;
						break;
					}
				}
				if(flag) {
					for (int[] pos : list) {
						pos[0]++;
					}
				}
			}

			for (int[] pos : list) {
				arr[pos[0]][pos[1]] = 'x';
			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		br.close();
		System.out.print(sb);
	}
}
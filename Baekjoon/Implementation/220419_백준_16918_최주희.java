import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		char[][] arr = new char[r][c];
		int[][] second = new int[r][c];
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

		for (int i = 0; i < r; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if(arr[i][j] == 'O') second[i][j] = 1;
			}
		}

		for (int t = 3; t <= n+1; t++) {
			if(t % 2 == 0) {
				Queue<int[]> q = new ArrayDeque<>();
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if(arr[i][j] == 'O' && second[i][j] + 3 == t) { //3초 지나면 폭발
							arr[i][j] = '.';
							second[i][j] = 0;
							q.offer(new int[]{i,j});
						}
					}
				}

				while(!q.isEmpty()) {
					int[] x = q.poll();

					for (int k = 0; k < 4; k++) {  //인접 폭탄 파괴
						int nx = x[0] + dx[k];
						int ny = x[1] + dy[k];

						if(nx<0 || nx>=r || ny<0 || ny>=c || arr[nx][ny] == '.') continue;
						arr[nx][ny] = '.';
						second[nx][ny] = 0;
					}
				}
			}

			else {
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if (arr[i][j] == '.') {  //폭탄 설치
							arr[i][j] = 'O';
							second[i][j] = t;
						}
					}
				}
			}
		}

		for (int i = 0; i < r; i++) {
			sb.append(arr[i]).append('\n');
		}
		br.close();
		System.out.print(sb);
	}
}
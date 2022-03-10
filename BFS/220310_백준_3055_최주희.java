import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] map = new char[r][c];
		Queue<int[]> wq = new ArrayDeque<>();
		Queue<int[]> sq = new ArrayDeque<>();
		boolean[][] visited = new boolean[r][c];
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if(map[i][j] == 'S') {
					sq.offer(new int[]{i,j});
					visited[i][j] = true;
				}
				else if(map[i][j] == '*') {
					wq.offer(new int[]{i,j});
				}
			}
		}

		int time = 0;
		while(!sq.isEmpty()) {
			time++;
			//물 이동
			int wqsize = wq.size();
			for (int i = 0; i < wqsize; i++) {
				int[] x = wq.poll();

				for (int j = 0; j < 4; j++) {
					int nx = x[0] + dx[j];
					int ny = x[1] + dy[j];

					if(nx<0 || nx>=r || ny<0 || ny>=c || map[nx][ny]!='.') continue;
					map[nx][ny] = '*';
					wq.offer(new int[]{nx,ny});
				}
			}

			//고슴도치 이동
			int sqsize = sq.size();
			for (int i = 0; i < sqsize; i++) {
				int[] s = sq.poll();
				for (int j = 0; j < 4; j++) {
					int nx = s[0] + dx[j];
					int ny = s[1] + dy[j];

					if(nx<0 || nx>=r || ny<0 || ny>=c || map[nx][ny] == '*' || map[nx][ny] == 'X' || visited[nx][ny]) continue;
					if(map[nx][ny] == 'D') {
						System.out.print(time);
						return;
					}
					visited[nx][ny] = true;
					sq.offer(new int[]{nx,ny});
				}
			}
		}
		br.close();
		System.out.print("KAKTUS");
	}
}
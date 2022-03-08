import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		int[][][] visited = new int[n][m][k+1];
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		if(n==1 && m==1) {
			if(map[0][0] == '1') System.out.print(-1);
			else System.out.println(1);
			return;
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{0,0,0});  //x,y,벽부순개수
		visited[0][0][0] = 1;  //visited가 홀수이면 낮, 짝수면 밤

		while(!q.isEmpty()) {
			int x[] = q.poll();

			for(int i=0; i<4; i++) {
				int nx = x[0] + dx[i];
				int ny = x[1] + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				if (nx == n - 1 && ny == m - 1) {
					System.out.print(visited[x[0]][x[1]][x[2]] + 1);
					return;
				}
				if (map[nx][ny] == '0' && visited[nx][ny][x[2]] == 0) {
					q.offer(new int[] {nx, ny, x[2]});
					visited[nx][ny][x[2]] = visited[x[0]][x[1]][x[2]] + 1;
				}
				//다음칸이 벽이고 부신벽이 k를 넘지 않았고, 현재 낮이며, 다음칸이 방문하지 않은 곳이면
				else if (map[nx][ny] == '1' && x[2] < k
					&& visited[x[0]][x[1]][x[2]] % 2 == 1 && visited[nx][ny][x[2] + 1] == 0) {
					q.offer(new int[] {nx, ny, x[2] + 1});
					visited[nx][ny][x[2] + 1] = visited[x[0]][x[1]][x[2]] + 1;
				}
			}
			if (visited[x[0]][x[1]][x[2]] % 2 == 0) {  //현재 밤이면, 같은 칸에 머무르며 낮으로 바꾼다
				q.offer(new int[] {x[0], x[1], x[2]});
				visited[x[0]][x[1]][x[2]]++;
			}
		}
		br.close();
		System.out.print(-1);
	}
}
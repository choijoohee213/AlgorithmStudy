import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		int[][][] visited = new int[n][m][k+1];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{0,0,0});
		visited[0][0][0] = 1;

		while(!q.isEmpty()) {
			int[] x = q.poll();
			if(x[0] == n-1 && x[1] == m-1) {
				System.out.print(visited[x[0]][x[1]][x[2]]);
				return;
			}

			for(int i=0; i<4; i++) {
				int nx = x[0] + dx[i];
				int ny = x[1] + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;

				if(map[nx][ny] == '0') {
					if(visited[nx][ny][x[2]] > 0) continue;
					q.offer(new int[]{nx,ny,x[2]});
					visited[nx][ny][x[2]] = visited[x[0]][x[1]][x[2]] + 1;
				}
				else if(x[2] < k){
					if(visited[nx][ny][x[2]+1] > 0) continue;
					q.offer(new int[]{nx,ny,x[2]+1});
					visited[nx][ny][x[2]+1] = visited[x[0]][x[1]][x[2]] + 1;
				}

			}
		}
		br.close();
		System.out.print(-1);
	}
}
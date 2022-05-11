import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[][] visited = new boolean[n][n];
		int[] dx = {-2,-2,0,0,2,2}, dy = {-1,1,-2,2,-1,1};

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{r1,c1,0});
		visited[r1][c1] = true;

		while(!q.isEmpty()) {
			int[] x = q.poll();
			if(x[0] == r2 && x[1] == c2) {
				System.out.print(x[2]);
				return;
			}
			for(int i=0; i<6; i++) {
				int nx = x[0] + dx[i];
				int ny = x[1] + dy[i];

				if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny]) continue;
				q.offer(new int[]{nx,ny,x[2]+1});
				visited[nx][ny] = true;
			}
		}
		br.close();
		System.out.print(-1);
	}
}
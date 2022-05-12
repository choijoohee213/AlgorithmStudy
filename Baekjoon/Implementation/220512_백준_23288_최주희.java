import java.io.*;
import java.util.*;

class Main {
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		int[] dice = new int[6];
		for (int i = 0; i < 6; i++) dice[i] = i+1;

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int x = 0, y = 0, dir = 0, score = 0;

		while(k-- > 0) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			//이동방향에 칸이 없다면 이동방향을 반대로 한다음 한 칸 굴러가기
			if(nx<0 || nx>=n || ny<0 || ny>=m) {
				dir = (dir + 2) % 4;
				nx = x + dx[dir];
				ny = y + dy[dir];
			}
			//점수 구하기
			score += bfs(nx,ny,n,m,arr) * arr[nx][ny];

			int[] tmp = dice.clone();
			if(dir == 0) { //동
				dice[2] = tmp[0];
				dice[5] = tmp[2];
				dice[0] = tmp[3];
				dice[3] = tmp[5];
			}
			else if(dir == 1) {  //남
				dice[1] = tmp[5];
				dice[5] = tmp[4];
				dice[4] = tmp[0];
				dice[0] = tmp[1];
			}
			else if(dir == 2) {  //서
				dice[3] = tmp[0];
				dice[2] = tmp[5];
				dice[5] = tmp[3];
				dice[0] = tmp[2];
			}
			else if(dir == 3) {  //북
				dice[1] = tmp[0];
				dice[5] = tmp[1];
				dice[4] = tmp[5];
				dice[0] = tmp[4];
			}
			if(dice[5] > arr[nx][ny]) {
				dir = (dir + 1) % 4;
			} else if(dice[5] < arr[nx][ny]) {
				dir = (dir + 3 ) % 4;
			}
			x = nx;
			y = ny;
		}
		br.close();
		System.out.print(score);
	}

	static int bfs(int sx, int sy, int n, int m, int[][] arr) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(sx*m+sy);
		boolean[][] visited = new boolean[n][m];
		visited[sx][sy] = true;
		int num = arr[sx][sy], cnt = 1;

		while(!q.isEmpty()) {
			int x = q.peek() / m;
			int y = q.peek() % m;
			q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx<0 || nx>=n || ny<0 || ny>=m ||visited[nx][ny] || num != arr[nx][ny]) continue;
				cnt++;
				visited[nx][ny] = true;
				q.offer(nx*m+ny);
			}
		}
		return cnt;
	}
}
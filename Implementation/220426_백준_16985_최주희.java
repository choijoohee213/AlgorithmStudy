import java.io.*;
import java.util.*;

class Main {
	static int[] order = new int[5];
	static int[] dir = new int[5];
	static int[][][] arr = new int[5][5][5];
	static int[][][] newArr = new int[5][5][5];
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < 5; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		permutation(0, new boolean[5]);
		br.close();
		System.out.print(result == Integer.MAX_VALUE ? -1 : result);
	}

	//5개 판 쌓는 순서 정하기 (순열)
	static void permutation(int cnt, boolean[] visited) {
		if(cnt == 5) {
			newArr = new int[5][5][5];
			rotate(0);
			return;
		}
		for (int i = 0; i < 5; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			order[cnt] = i;
			permutation(cnt+1, visited);
			visited[i] = false;
		}
	}

	//4 방향 중 회전 (중복 순열)
	static void rotate(int cnt) {
		if(cnt == 5) {
			for (int i = 0; i < 5; i++) {
				int num = order[i];
				int d = dir[num];
				for (int x = 0; x < 5; x++) { //회전
					for (int y = 0; y < 5; y++) {
						if(d == 0) newArr[i][x][y] = arr[num][x][y];
						else if(d == 1) newArr[i][x][y] = arr[num][4-y][x];
						else if(d == 2) newArr[i][x][y] = arr[num][4-x][4-y];
						else if(d == 3) newArr[i][x][y] = arr[num][y][4-x];
					}
				}
			}
			if(newArr[0][0][0] == 1 && newArr[4][4][4] == 1) bfs();
			return;
		}

		for (int i = 0; i < 4; i++) {  //회전 방향 정하기
			dir[cnt] = i;
			rotate(cnt+1);
		}
	}

	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[5][5][5];
		int[] dx = {0,0,0,0,1,-1}, dy = {0,1,0,-1,0,0}, dz = {1,0,-1,0,0,0};
		q.offer(new int[]{0,0,0,0}); //x,y,z,이동횟수
		visited[0][0][0] = true;

		while(!q.isEmpty()) {
			int[] x = q.poll();

			if(x[0] == 4 && x[1] == 4 && x[2] == 4) {
				result = Math.min(result, x[3]);
				if(result == 12) {
					System.out.print(12);
					System.exit(0);
				}
				return;
			}

			for (int i = 0; i < 6; i++) {
				int nx = x[0] + dx[i];
				int ny = x[1] + dy[i];
				int nz = x[2] + dz[i];

				if(nx<0 || nx>=5 || ny<0 || ny>=5 || nz<0 || nz>=5
					|| visited[nx][ny][nz] || newArr[nx][ny][nz] == 0) continue;
				visited[nx][ny][nz] = true;
				q.offer(new int[]{nx,ny,nz,x[3]+1});
			}
		}
	}

}
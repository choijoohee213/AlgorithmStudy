import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		boolean[][][] visited = new boolean[n][n][2];
		int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
		int[] b_tree = new int[3];
		int[] e_tree = new int[3];
		int bIdx = 0, eIdx = 0, eDir;

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				char c = s.charAt(j);
				if(c == 'B') {
					arr[i][j] = 2;
					b_tree[bIdx++] = i*n+j;
				}
				else if(c == 'E') {
					arr[i][j] = 3;
					e_tree[eIdx++] = i*n+j;
				}
				else arr[i][j] = c - '0';
			}
		}
		br.close();
		eDir = e_tree[0]/n == e_tree[1]/n ? 0 : 1;

		Queue<int[]> q = new ArrayDeque<>(); //x,y,방향,횟수
		int sDir = b_tree[0]/n == b_tree[1]/n ? 0 : 1; //세로면 1, 가로면 0
		q.offer(new int[]{b_tree[1]/n, b_tree[1]%n, sDir, 0});
		visited[b_tree[1]/n][b_tree[1]%n][sDir] = true;

		loop1 : while(!q.isEmpty()) {
			int[] tree = q.poll();

			if(tree[0] == e_tree[1]/n && tree[1] == e_tree[1]%n && tree[2] == eDir) {
				System.out.print(tree[3]);
				return;
			}

			loop2 : for (int i = 0; i < 4; i++) {
				int nx = tree[0] + dx[i];
				int ny = tree[1] + dy[i];

				if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny][tree[2]]) continue;
				if(tree[2] == 0) { //가로
					if(i == 2 || i == 3) {  //좌우방향
						int yy = tree[1] + 2 * dy[i];
						if(yy<0 || yy>=n || arr[tree[0]][yy] == 1) continue;
					}
					else {  //상하방향
						for (int j = -1; j <= 1; j++) {
							int xx = tree[0] + dx[i];
							int yy = tree[1] + j;
							if(xx<0 || xx>=n || yy<0 || yy>=n || arr[xx][yy] == 1) continue loop2;
						}
					}
				} else {  //세로
					if(i == 2 || i == 3) {  //좌우방향
						for (int j = -1; j <= 1; j++) {
							int xx = tree[0] + j;
							int yy = tree[1] + dy[i];
							if(xx<0 || xx>=n || yy<0 || yy>=n || arr[xx][yy] == 1) continue loop2;
						}
					} else {  //상하방향
						int xx = tree[0] + 2 * dx[i];
						if(xx<0 || xx>=n || arr[xx][tree[1]] == 1) continue;
					}
				}
				visited[nx][ny][tree[2]] = true;
				q.offer(new int[]{nx,ny,tree[2],tree[3]+1});
			}

			//회전
			int nDir = tree[2] == 1 ? 0 : 1;
			if(visited[tree[0]][tree[1]][nDir]) continue;
			for (int i = -1; i <= 1; i++) {  //회전 체크 3*3
				for (int j = -1; j <= 1; j++) {
					int xx = tree[0] + i;
					int yy = tree[1] + j;
					if(xx<0 || xx>=n || yy<0 || yy>=n || arr[xx][yy] == 1) continue loop1;
				}
			}
			visited[tree[0]][tree[1]][nDir] = true;
			q.offer(new int[]{tree[0],tree[1],nDir,tree[3]+1});
		}
		System.out.print(0);
	}
}
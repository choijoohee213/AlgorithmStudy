import java.io.*;
import java.util.*;

class Pos {
	public int x, y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Main {
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	static int n,m, result = Integer.MAX_VALUE;
	static char[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int redX=0, redY=0, blueX=0, blueY=0;
		arr = new char[n][m];

		for(int i=0; i<n; i++) {
			arr[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(arr[i][j] == 'R') {  //빨간색 구슬 위치
					redX = i;
					redY = j;
					arr[i][j] = '.';
				} else if(arr[i][j] == 'B') {  //파란색 구슬 위치
					blueX = i;
					blueY = j;
					arr[i][j] = '.';
				}
			}
		}
		dfs(redX, redY, blueX, blueY, 0);
		br.close();
		System.out.print(result == Integer.MAX_VALUE ? -1 : result);
	}

	static void dfs(int rx, int ry, int bx, int by, int cnt) {
		if(cnt >= 10) return;  //
		for(int i=0; i<4; i++) {
			Pos r = move(rx, ry, i);
			Pos b = move(bx, by, i);
			if(arr[b.x][b.y] == 'O') continue;
			if(arr[r.x][r.y] == 'O') {
				result = Math.min(result, cnt+1);
				return;
			}
			if(r.x == b.x && r.y == b.y) {
				int diffR = Math.abs(r.x - rx) + Math.abs(r.y - ry);
				int diffB = Math.abs(b.x - bx) + Math.abs(b.y - by);
				if(diffR > diffB) {
					r.x -= dx[i];
					r.y -= dy[i];
				} else if(diffR < diffB){
					b.x -= dx[i];
					b.y -= dy[i];
				}
			}
			dfs(r.x, r.y, b.x, b.y, cnt+1);
		}
	}

	static Pos move(int x, int y, int i) {
		while(true) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx<0 || nx>=n || ny<0 || ny>=m) break;
			if(arr[nx][ny] == '#') break;
			x += dx[i];
			y += dy[i];
			if(arr[nx][ny] == 'O') break;
		}
		return new Pos(x, y);
	}
}
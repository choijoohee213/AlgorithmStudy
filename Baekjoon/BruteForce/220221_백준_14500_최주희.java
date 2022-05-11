import java.io.*;
import java.util.*;

class Main {
	static int n, m, result = 0;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new boolean[n][m];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				visited[i][j] = true;
				dfs(i, j,1, arr[i][j]);
				visited[i][j] = false;
				checkTheOtherOne(i,j);
			}
		}
		br.close();
		System.out.print(result);
	}

	static void dfs(int x, int y, int cnt, int sum) {  //4개가 이어진 것 찾기
		if(cnt == 4) {
			result = Math.max(result, sum);
			return;
		}
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx<0 || nx>=n || ny<0 || ny>=m || visited[nx][ny]) continue;
			visited[nx][ny] = true;
			dfs(nx, ny, cnt+1, sum + arr[nx][ny]);
			visited[nx][ny] = false;
		}
	}

	static void checkTheOtherOne(int x, int y) {  //분홍색 테트로미노(ㅜ)는 따로 처리
		if(x+1<n && y+2<m){  //ㅜ
			result = Math.max(result, arr[x][y] + arr[x][y+1] + arr[x][y+2] + arr[x+1][y+1]);
		}
		if(x-1>=0 && y+2<m) {  //ㅗ
			result = Math.max(result, arr[x][y] + arr[x][y+1] + arr[x][y+2] + arr[x-1][y+1]);
		}
		if(x+2<n && y+1<m) {  //ㅏ
			result = Math.max(result, arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x+1][y+1]);
		}
		if(x+2<n && y-1>=0) { //ㅓ
			result = Math.max(result, arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x+1][y-1]);
		}
	}
}
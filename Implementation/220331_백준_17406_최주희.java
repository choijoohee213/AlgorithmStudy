import java.io.*;
import java.util.*;

class Main {
	static int n,m,k,result=Integer.MAX_VALUE;
	static int[][] arr, rotation;
	static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		rotation = new int[k][3];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			rotation[i][0] = r;
			rotation[i][1] = c;
			rotation[i][2] = s;
		}
		permutation(0, new boolean[k], new int[k]);
		System.out.print(result);
	}

	static void permutation(int cnt, boolean[] visited, int[] picked) {
		if(cnt == k) {
			int[][] board = new int[n][m];
			for (int i = 0; i < n; i++) {
				System.arraycopy(arr[i], 0, board[i], 0, m);
			}
			for(int i=0; i<k; i++) rotate(picked[i], board);
			calVal(board);
			return;
		}

		for(int i=0; i<k; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			picked[cnt] = i;
			permutation(cnt+1, visited, picked);
			visited[i] = false;
		}
	}

	static void rotate(int idx, int[][] board) {
		int r = rotation[idx][0];
		int c = rotation[idx][1];
		int s = rotation[idx][2];
		int r_min = r-s, c_min = c-s, len = 2*s;

		for(int k=0; k<s; k++) {
			int x = r_min, y = c_min, firstVal = board[x][y];
			for(int i=0; i<4; i++) {
				for(int j=0; j<len; j++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					board[x][y] = board[nx][ny];
					x = nx;
					y = ny;
				}
			}
			board[r_min][c_min+1] = firstVal;
			r_min++;
			c_min++;
			len -= 2;
		}
	}

	static void calVal(int[][] board) {
		int minSum = Integer.MAX_VALUE;
		for(int i=0; i<n; i++) {
			int sum = 0;
			for(int j=0; j<m; j++) {
				sum += board[i][j];
			}
			minSum = Math.min(minSum , sum);
		}
		result = Math.min(result, minSum);
	}

}
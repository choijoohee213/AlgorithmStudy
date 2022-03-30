import java.io.*;
import java.util.*;

class Main {
	static int n,m,h,result = Integer.MAX_VALUE;
	static boolean flag = false;
	static int[][] ladder;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		ladder = new int[h+1][n+1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][b] = 1; //오른쪽으로
			ladder[a][b+1] = 2;  //왼쪽으로
		}
		for (int i = 0; i <= 3; i++) {
			dfs(1, 0, i);
			if(flag) break;
		}
		br.close();
		System.out.print(flag?result:-1);
	}

	static void dfs(int idx, int cnt, int maxCnt) {
		if(flag) return;
		if(cnt == maxCnt) {
			if(check()) {
				result = Math.min(result, cnt);
			}
			return;
		}

		for (int i = idx; i <= h; i++) {
			for (int j = 1; j < n; j++) {
				if(ladder[i][j] == 0 && ladder[i][j+1] == 0) {  //우측으로 가로선이 존재하지 않으면
					ladder[i][j] = 1;
					ladder[i][j+1] = 2;
					dfs(i, cnt+1, maxCnt);
					ladder[i][j] = 0;
					ladder[i][j+1] = 0;
 				}
			}
		}
	}

	static boolean check() {
		for (int i = 1; i <= n; i++) {
			int x = 1, y = i;  //x : 가로선, y : 세로선
			for (int j = 0; j < h; j++) {
				if(ladder[x][y] == 1) y++;
				else if(ladder[x][y] == 2) y--;
				x++;
			}
			if(y != i) return false;
		}
		flag = true;
		return true;
	}
}
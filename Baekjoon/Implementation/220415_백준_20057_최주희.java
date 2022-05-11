import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dx = {0,1,0,-1}, dy = {-1,0,1,0};
		int[] px = {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0}, py = {0, -1, 0, 1, -2, -1, 0, 1, 0, -1};
		int[] percent = {2,10,7,1,5,10,7,1,2};
		int result = 0;
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int dir = 0, x = (n-1)/2, y = (n-1)/2;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < 2; j++) {
				if(i == n && j == 1) {
					br.close();
					System.out.print(result);
					return;
				}
				for (int k = 0; k < i; k++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];

					if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
					int sand = arr[nx][ny];
					int minus = 0;
					for (int l = 0; l < 10; l++) {
						int xx, yy;
						if(dir == 0) {
							xx = nx + px[l];
							yy = ny + py[l];
						} else if(dir == 1) {
							xx = nx + py[l] * -1;
							yy = ny + px[l];
						} else if(dir == 2) {
							xx = nx + px[l];
							yy = ny + py[l] * -1;
						} else {
							xx = nx + py[l];
							yy = ny + px[l] * -1;
						}

						if(l<9) minus += sand * percent[l] / 100;
						if(xx<0 || xx>=n || yy<0 || yy>=n) {
							if(l<9) result += sand * percent[l] / 100;
							else result += sand - minus;
						} else {
							if(l<9) arr[xx][yy] += sand * percent[l] / 100;
							else arr[xx][yy] += sand - minus;
						}
					}
					arr[nx][ny] = 0;
					x = nx;
					y = ny;
				}
				dir = (dir+1) % 4;
			}
		}
	}
}
import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int[][] sticker = new int[r][c];

			for (int j = 0; j < r; j++) {
				st = new StringTokenizer(br.readLine());
				for (int l = 0; l < c; l++) {
					sticker[j][l] = Integer.parseInt(st.nextToken());
				}
			}

			for (int dir = 0; dir < 4; dir++) {
				boolean stick = false;
				check : for (int j = 0; j <= n-r; j++) {
					for (int l = 0; l <= m-c; l++) {
						boolean flag = true;
						loop : for (int x = 0; x < r; x++) {
							for (int y = 0; y < c; y++) {
								if(arr[j+x][l+y] != 0 && sticker[x][y] == 1) {
									flag = false;
									break loop;
								}
							}
						}
						if(flag) { //스티커를 붙일 수 있다면 붙이기
							for (int x = 0; x < r; x++) {
								for (int y = 0; y < c; y++) {
									arr[j+x][l+y] += sticker[x][y];
								}
							}
							stick = true;
							break check;
						}
					}
				}
				if(stick || dir == 3) break;
				int[][] newSticker = new int[c][r];  //90도씩 회전
				for (int j = 0; j < c; j++) {
					for (int l = 0; l < r; l++) {
						newSticker[j][l] = sticker[r-l-1][j];
					}
				}
				int tmp = r;
				r = c;
				c = tmp;
				sticker = newSticker;
			}
		}

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j] != 0) cnt++;
			}
		}
		br.close();
		System.out.print(cnt);
	}
}
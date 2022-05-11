import java.io.*;
import java.util.*;

/**
 * 블록 종류 세가지 : 1*1, 1*2, 2*1
 * 빨간색 보드에서 블록 놓을 위치 지정
 * 	- 그 위치부터 초록색, 파란색 보드로 블록이 이동(블록이나 보드 경계까지)
 * 초록색 보드는 한 행이 가득차면 그 행 블록은 사라지고 1점 (위의 블록들은 아래로 이동)
 * 파란색 보드는 한 열이 가득차면 그 열 블록은 사라지고 1점 (왼쪽의 블록들이 오른쪽으로 이동)
 * 연한 초록색 보드는 0,1번 행의 블록이 있는 행의 수만큼 아래 행에 있는 블록이 사라짐 (모든 블록이 사라진 행수만큼 아래로 이동)
 * 연한 파란색 보드는 0,1번 열의 블록이 있는 열의 수만큼 오른쪽 열에 있는 블록이 사라짐 (모든 블록이 사라진 열수만큼 오른쪽 이동)
 * 행/열 가득찬 경우와 연한칸에 블록이 있는 경우가 동시에 발생 -> 행/열 처리 다음에 연한칸 블록 처리
 */

class Main {
	static boolean[][] green = new boolean[6][4];
	static boolean[][] blue = new boolean[4][6];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine()), score = 0;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if(t == 1) {
				addBlockGreen(y);
				addBlockBlue(x);
			} else if(t == 2) {
				addBlockGreen(y,y+1);
				int newY = addBlockBlue(x);
				blue[x][newY-1] = true;
			} else {
				int newX = addBlockGreen(y);
				green[newX-1][y] = true;
				addBlockBlue(x,x+1);
			}

			//초록색 보드 점수 체크
			for (int r = 0; r < 6; r++) {
				boolean flag = true;
				for (int c = 0; c < 4; c++) {
					if(!green[r][c]) {
						flag = false;
						break;
					}
				}
				if(flag) {  //한행이 가득찼을 경우
					score++;
					for (int j = r; j > 0; j--) {
						for (int k = 0; k < 4; k++) {
							green[j][k] = green[j-1][k];
						}
					}
					for (int j = 0; j < 4; j++) {
						green[0][j] = false;
					}
				}
			}

			//파란색 보드 점수 체크
			for (int c = 0; c < 6; c++) {
				boolean flag = true;
				for (int r = 0; r < 4; r++) {
					if(!blue[r][c]) {
						flag = false;
						break;
					}
				}
				if(flag) {  //한열이 가득찼을 경우
					score++;
					for (int j = c; j > 0; j--) {
						for (int k = 0; k < 4; k++) {
							blue[k][j] = blue[k][j-1];
						}
					}
					for (int j = 0; j < 4; j++) {
						blue[j][0] = false;
					}
				}
			}

			//연한 초록색 체크
			boolean green0 = false, green1 = false;
			for (int j = 0; j < 4; j++) {
				if(green[0][j]) green0 = true;
				if(green[1][j]) green1 = true;
			}
			int cnt = green0 ? 1 : 0;
			if(green1) cnt++;

			if(cnt > 0) {
				for (int j = 5; j >= cnt; j--) {
					for (int k = 0; k < 4; k++) {
						green[j][k] = green[j - cnt][k];
					}
				}
				while(cnt-- > 0) {
					for (int j = 0; j < 4; j++) {
						green[cnt][j] = false;
					}
				}
			}

			//연한 파란색 체크
			boolean blue0 = false, blue1 = false;
			for (int j = 0; j < 4; j++) {
				if(blue[j][0]) blue0 = true;
				if(blue[j][1]) blue1 = true;
			}
			cnt = blue0 ? 1 : 0;
			if(blue1) cnt++;

			if(cnt > 0) {
				for (int j = 5; j >= cnt; j--) {
					for (int k = 0; k < 4; k++) {
						blue[k][j] = blue[k][j-cnt];
					}
				}
				while(cnt-- > 0) {
					for (int j = 0; j < 4; j++) {
						blue[j][cnt] = false;
					}
				}
			}
		}

		//타일 개수 세기
		int cnt = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if(green[i][j]) cnt++;
				if(blue[j][i]) cnt++;
			}
		}
		br.close();
		System.out.println(score);
		System.out.print(cnt);
	}

	static int addBlockGreen(int y) {
		int x = 0;
		for (; x < 6; x++) {
			if(green[x][y]) break;
		}
		green[x-1][y] = true;
		return x-1;
	}

	static void addBlockGreen(int y1, int y2) {
		int x = 0;
		for (; x < 6; x++) {
			if(green[x][y1] || green[x][y2]) break;
		}
		green[x-1][y1] = true;
		green[x-1][y2] = true;
	}

	static int addBlockBlue(int x) {
		int y = 0;
		for (; y < 6; y++) {
			if(blue[x][y]) break;
		}
		blue[x][y-1] = true;
		return y-1;
	}

	static void addBlockBlue(int x1, int x2) {
		int y = 0;
		for (; y < 6; y++) {
			if(blue[x1][y] || blue[x2][y]) break;
		}
		blue[x1][y-1] = true;
		blue[x2][y-1] = true;
	}
}
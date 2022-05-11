import java.io.*;
import java.util.*;

class Main {
	static boolean flag;
	static int[][] map;
	static boolean[][] domino;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 0;
		while(++tc>0) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			map = new int[9][9];
			domino = new boolean[10][10];  //도미노 조합
			flag = false;
			sb.append("Puzzle ").append(tc).append("\n");

			for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int u = Integer.parseInt(st.nextToken());
				String lu = st.nextToken();
				map[lu.charAt(0)-'A'][lu.charAt(1)-'1'] = u;

				int v = Integer.parseInt(st.nextToken());
				lu = st.nextToken();
				map[lu.charAt(0)-'A'][lu.charAt(1)-'1'] = v;
				domino[u][v] = true;
				domino[v][u] = true;
			}
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<=9; i++) {
				String lu = st.nextToken();
				map[lu.charAt(0)-'A'][lu.charAt(1)-'1'] = i;  //숫자 채우기
			}
			recursive(0, 0);
		}
		br.close();
		System.out.print(sb);
	}

	static void recursive(int x, int y) {
		if(flag) return;
		if(y == 9) {
			recursive(x+1, 0);  //다음 행
			return;
		}
		if(x == 9) {
			//출력
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			flag = true;  //그만 멈춘다.
			return;
		}

		if(map[x][y] == 0) {  //빈 공간이라면
			for(int i=0; i<2; i++) {  //왼쪽부터 채워지기떄문에 오른쪽, 아래만
				int nx = x + i;
				int ny = y + Math.abs(i-1);

				if(nx<0 || nx>=9 || ny<0 || ny>=9 || map[nx][ny]!=0) continue;

				for(int j=1; j<=9; j++) {
					for(int k=j+1; k<=9; k++) {
						if(domino[j][k] || domino[k][j]) continue;
						assign(x, y, nx, ny, j, k);
						assign(x, y, nx, ny, k, j);
					}
				}
			}
 		} else {
			recursive(x, y+1);
		}
	}

	static void assign(int x, int y, int nx, int ny, int j, int k) {
		if(check(x,y,j) && check(nx,ny,k)) {
			map[x][y] = j;
			map[nx][ny] = k;
			domino[j][k] = true;
			domino[k][j] = true;

			recursive(x, y+1);

			map[x][y] = 0;
			map[nx][ny] = 0;
			domino[j][k] = false;
			domino[k][j] = false;
		}
	}

	static boolean check(int x, int y, int num) {
		for(int i=0; i<9; i++) {
			if(map[x][i] == num || map[i][y] == num) return false;  //행,열 체크
		}
		//3*3 체크
		int sx = x / 3 * 3;
		int sy = y / 3 * 3;
		for(int i = sx; i<sx+3; i++) {
			for(int j=sy; j<sy+3; j++) {
				if(map[i][j] == num) return false;
			}
		}
		return true;
	}
}
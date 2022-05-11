import java.io.*;

/**
 * 가로/세로 길이의 변화 (※ n = 1인 초기값 제외)
 * (가로) 1 → 5 → 9 → 13 → 4(n-1) + 1 = 4n - 3
 * (세로) 1 → 7 → 11 → 15 →  가로 길이 + 2
 *
 * ① 왼쪽으로 width만큼 별 그리기
 * ② 아래쪽으로 height만큼 별 그리기
 * ③ 오른쪽으로 width만큼 별 그리기
 * ④ 위로 height - 2 만큼 별 그리기
 * ⑤ 왼쪽으로 별 1개를 그립니다.
 * ⑥ n > 3일 때는 위의 과정을 수행한 후 다음 단계의 시작점을 매개변수로 넘겨주면 되지만
 * 		n = 2일 때는 마무리 단계로 세로 방향으로 별 3개를 연속적으로 그려서 종료합니다.
 * 		(※ 입력받은 n = 1인 경우 별 한개만 출력하여 return)
 */
class Main {
	static char[][] map = new char[399][397];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		if (n == 1) {
			System.out.print("*");
			return;
		}
		int width = 4 * n - 3;
		int height = width + 2;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				map[i][j] = ' ';
			}
		}
		int x = 0, y = 4 * n - 4;
		drawStar(n, x, y);

		for (int i = 0; i < height; i++) {
			//두번째 줄 예외처리
			if (i == 1) {
				sb.append("*\n");
				continue;
			}

			for (int j = 0; j < width; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		br.close();
		System.out.print(sb);
	}

	static void drawStar(int n, int x, int y) {
		int width = 4 * n - 3;
		int height = width + 2;

		for (int i = 1; i < width; i++) map[x][y--] = '*';
		for (int i = 1; i < height; i++) map[x++][y] = '*';
		for (int i = 1; i < width; i++) map[x][y++] = '*';
		for (int i = 1; i < height - 2; i++) map[x--][y] = '*';

		map[x][y] = '*';
		y--;
		map[x][y] = '*';
		if (n == 2) {
			map[x][y-1] = '*';
			map[x+1][y-1] = '*';
			map[x+2][y-1] = '*';
			return;
		}
		drawStar(n-1, x, y-1);
	}
}
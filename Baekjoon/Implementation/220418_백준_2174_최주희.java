import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] dx = {0,-1,0,1}, dy = {1,0,-1,0};  //E,N,W,S
		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[][] arr = new int[r][c];

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Map<Integer, Integer> robot = new HashMap<>();
		int[] robotInfo = new int[r*c];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = r - Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			if(dir == 'E') arr[x][y] = 0;
			else if(dir == 'N') arr[x][y] = 1;
			else if(dir == 'W') arr[x][y] = 2;
			else arr[x][y] = 3;
			robot.put(i+1, x*c+y);
			robotInfo[x*c+y] = i+1;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			char cmd = st.nextToken().charAt(0);
			int count = Integer.parseInt(st.nextToken());

			int x = robot.get(num) / c;
			int y = robot.get(num) % c;
			int dir = arr[x][y];
			if(cmd == 'L') {
				count %= 4;
				arr[x][y] = (arr[x][y] + count) % 4;
			} else if(cmd == 'R') {
				count %= 4;
				arr[x][y] = (arr[x][y] + 4 - count) % 4;
			} else {
				int nx = x, ny = y;
				for (int j = 0; j < count; j++) {
					nx += dx[dir];
					ny += dy[dir];

					if(nx<0 || nx>=r || ny<0 || ny>=c) {
						System.out.printf("Robot %d crashes into the wall", num);
						return;
					}
					if(robotInfo[nx*c+ny] != 0) {
						System.out.printf("Robot %d crashes into robot %d", num, robotInfo[nx*c+ny]);
						return;
					}
				}
				robotInfo[nx*c+ny] = num;
				robotInfo[x*c+y] = 0;
				robot.put(num, nx*c+ny);
				arr[x][y] = 0;
				arr[nx][ny] = dir;
			}
		}
		br.close();
		System.out.print("OK");
	}
}
import java.io.*;
import java.util.*;

class Shark {
	public int num, smell;
	public boolean isShark;

	public Shark(int num, int smell) {
		this.num = num;
		this.smell = smell;
		this.isShark = true;
	}

	public void remainSmell(int k) {
		smell = k-1;
		isShark = false;
	}
}

class Main {
	static int n,m,k,cnt;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static Shark[][] map, newMap;
	static int[] sharkDir;
	static int[][][] priority;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		cnt = m;

		map = new Shark[n][n];
		sharkDir = new int[m+1];
		priority = new int[m+1][4][4];  //상 하 좌 우

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int x = Integer.parseInt(st.nextToken());
				if(x != 0) {
					map[i][j] = new Shark(x, k);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) {  //상어가 보고있는 방향 입력받기
			sharkDir[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		//각 상어의 우선순위 입력받기
		for (int i = 1; i <= m; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				priority[i][j][0] = Integer.parseInt(st.nextToken()) - 1;
				priority[i][j][1] = Integer.parseInt(st.nextToken()) - 1;
				priority[i][j][2] = Integer.parseInt(st.nextToken()) - 1;
				priority[i][j][3] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		br.close();

		for (int t = 1; t <= 1000; t++) {
			newMap = new Shark[n][n];  //새로운 맵 생성
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(map[i][j] == null || visited[i][j]) continue;

					if(map[i][j].isShark) {  //상어 이동
						move(i,j);
					} else {  //남아있는 냄새 -1
						newMap[i][j] = map[i][j];
						newMap[i][j].smell--;
						if(newMap[i][j].smell == 0)
							newMap[i][j] = null;
					}
					if(cnt == 1) {  //상어가 한마리 남았으면 종료
						System.out.print(t);
						return;
					}
				}
			}
			map = newMap;
		}
		System.out.print(-1);
	}

	static void move(int x, int y) {
		//우선순위에 맞춰 탐색
		int[] dir = priority[map[x][y].num][sharkDir[map[x][y].num]];
		for (int i = 0; i < 4; i++) {  //인접한 칸 중 빈칸으로 이동
			int nx = x + dx[dir[i]];
			int ny = y + dy[dir[i]];

			if(nx<0 || nx>=n || ny<0 || ny>=n || map[nx][ny] != null) continue;

			//원래있던 곳에 냄새남기기
			newMap[x][y] = map[x][y];
			newMap[x][y].remainSmell(k);

			//여러 마리 상어가 남아 있으면, 가장 작은 번호를 가진 상어만 남게 됨
			if(newMap[nx][ny] != null && newMap[nx][ny].isShark) {
				cnt--;
				if(newMap[nx][ny].num < map[x][y].num) return;  //내가 더 크면 내가 죽게됨
			}
			newMap[nx][ny] = new Shark(map[x][y].num, k);  //새로운 곳으로 이동
			sharkDir[map[x][y].num] = dir[i];
			return;
		}

		//인접한 빈 칸이 없다면 자신의 냄새가 있는 칸으로
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[dir[i]];
			int ny = y + dy[dir[i]];

			if(nx<0 || nx>=n || ny<0 || ny>=n || map[nx][ny].num != map[x][y].num) continue;
			newMap[nx][ny] = new Shark(map[x][y].num, k);
			visited[nx][ny] = true;  //냄새-1을 하지않고 넘어가게 하기 위해서
			sharkDir[map[x][y].num] = dir[i];

			//원래있던 곳에 냄새남기기
			newMap[x][y] = map[x][y];
			newMap[x][y].remainSmell(k);
			return;
		}
	}
}
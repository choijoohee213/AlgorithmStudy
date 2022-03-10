import java.io.*;
import java.util.*;

class Main {
	static int n, shark = 2, sharkX, sharkY;
	static int[][] sea;
	static List<int[]> fishes = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		sea = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				sea[i][j] = Integer.parseInt(st.nextToken());
				if(sea[i][j] == 9){
					sharkX = i;
					sharkY = j;
					sea[i][j] = 0;
				}
			}
		}

		int eatCnt = 0, result = 0;
		while(true) {
			bfs();

			if(fishes.isEmpty()) break;
			fishes.sort((int[] a, int[] b) -> {  //거리짧은거->가장위에->가장왼쪽
				if (a[0] == b[0]) {
					if (a[1] == b[1])
						return a[2] - b[2];
					return a[1] - b[1];
				}
				return a[0] - b[0];
			});
			eatCnt++;
			if(eatCnt == shark) {
				shark++;
				eatCnt = 0;
			}
			int x = fishes.get(0)[1];
			int y = fishes.get(0)[2];
			result += fishes.get(0)[0];
			sea[x][y] = 0;
			sharkX = x;
			sharkY = y;
		}
		br.close();
		System.out.print(result);
	}

	static void bfs(){
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{sharkX,sharkY});
		fishes.clear();
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
		int[][] d = new int[n][n];
		int eatDistance = Integer.MAX_VALUE;

		while(!q.isEmpty()){
			int[] pos = q.poll();

			for(int i=0; i<4; i++){
				int nx = pos[0] + dx[i];
				int ny = pos[1] + dy[i];

				if(nx<0 || nx>=n || ny<0 || ny>=n || d[nx][ny]>0 || sea[nx][ny]>shark) continue;
				d[nx][ny] = d[pos[0]][pos[1]] + 1;
				if(sea[nx][ny]>0 && sea[nx][ny]<shark && eatDistance>=d[nx][ny]){
					eatDistance = d[nx][ny];
					fishes.add(new int[]{eatDistance, nx, ny});
				}
				q.offer(new int[]{nx,ny});
			}
		}
	}
}
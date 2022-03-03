import java.io.*;
import java.util.*;

class Main {
	static int n,m,sum=0, result=0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) sum++;
			}
		}
		comb(0, 0, map);  //조합으로 3개 인덱스 정해서 벽 세우기
		br.close();
		System.out.print(result);
	}

	static void comb(int idx, int cnt, int[][] map) {
		if(cnt == 3) {
			//bfs
			int total = sum;
			int[][] arr = new int[n][m];
			for(int i=0; i<n; i++) arr[i] = map[i].clone();
			int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(arr[i][j] != 2) continue;
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[]{i,j});

					while(!q.isEmpty()) {
						int[] a = q.poll();

						for(int k=0; k<4; k++) {
							int nx = a[0] + dx[k];
							int ny = a[1] + dy[k];

							if(nx<0 || nx>=n || ny<0 || ny>=m || arr[nx][ny] != 0) continue;
							arr[nx][ny] = 2;
							total--;
							q.offer(new int[]{nx,ny});
						}
					}
				}
			}
			result = Math.max(result, total-3);  //새로 세운 벽 3개 뺴주기
			return;
		}

		for(int i=idx; i<n*m; i++) {
			if(map[i/m][i%m] != 0) continue;
			map[i/m][i%m] = 1;
			comb(i+1, cnt+1, map);
			map[i/m][i%m] = 0;
		}
	}
}
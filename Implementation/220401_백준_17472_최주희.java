import java.io.*;
import java.util.*;

class Main {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int idx = 1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(arr[i][j] == 0 || visited[i][j]) continue;

				Queue<Integer> q = new ArrayDeque<>();
				q.offer(i*m+j);
				visited[i][j] = true;
				arr[i][j] = idx;

				while(!q.isEmpty()) {
					int x = q.peek() / m;
					int y = q.peek() % m;
					q.poll();

					for(int k=0; k<4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];

						if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || arr[nx][ny] == 0)
							continue;
						q.offer(nx * m + ny);
						visited[nx][ny] = true;
						arr[nx][ny] = idx;
					}
				}
				idx++;
			}
		}
		Queue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[2]-o2[2]<0?-1:1);

		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(arr[i][j] == 0) continue;

				//가로 세로 탐색하여 섬->섬 거리값 저장
				int nx = i, ny = j, len = 0;
				for (int k = 0; k < 4; k++) {
					while(true) {
						nx += dx[k];
						ny += dy[k];

						if(nx<0 || nx>=n || ny<0 || ny>=m) {
							len = 0;
							nx = i;
							ny = j;
							break;
						}
						if(arr[nx][ny] == arr[i][j]) { //같은 번호 나오면 초기화
							len = 0;
							nx = i;
							ny = j;
							break;
						} else if(arr[nx][ny] == 0) {
							len++;
						} else {
							if(len>1) pq.offer(new int[]{arr[nx][ny], arr[i][j], len});
							len = 0;
							nx = i;
							ny = j;
							break;
						}

					}
				}
			}
		}
		parent = new int[idx];
		for(int k=1; k<idx; k++) {
			parent[k] = k;
		}
		int result = 0, cnt = 0;
		while(!pq.isEmpty()) {
			int[] x = pq.poll();
			if(!sameParent(x[0],x[1])) {
				Union(x[0],x[1]);
				result += x[2];
				cnt++;
			}
		}
		br.close();
		System.out.print(cnt!=0 && cnt==(idx-2)?result:-1);
	}

	static int getParent(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = getParent(parent[x]);
	}

	static boolean sameParent(int x, int y) {
		return getParent(x) == getParent(y);
	}

	static void Union(int x, int y) {
		x = getParent(x);
		y = getParent(y);

		if(x<y) parent[y] = x;
		else parent[x] = y;
	}
}
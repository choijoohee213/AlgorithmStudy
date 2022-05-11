import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
		Queue<int[]>[] q = new Queue[k+1];
		for (int i = 1; i <= k; i++) {
			q[i] = new ArrayDeque<>();
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] != 0) q[arr[i][j]].offer(new int[]{i,j});
			}
		}
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int rx = Integer.parseInt(st.nextToken())-1;
		int ry = Integer.parseInt(st.nextToken())-1;

		while(s-- > 0 && arr[rx][ry] == 0) {
			for (int i = 1; i <= k; i++) {
				int size = q[i].size();
				for (int j = 0; j < size; j++) {
					int[] x = q[i].poll();

					for (int l = 0; l < 4; l++) {
						int nx = x[0] + dx[l];
						int ny = x[1] + dy[l];

						if(nx<0 || nx>=n || ny<0 || ny>=n || arr[nx][ny] != 0) continue;
						arr[nx][ny] = arr[x[0]][x[1]];
						q[arr[nx][ny]].offer(new int[]{nx,ny});
					}
				}
			}
		}
		br.close();
		System.out.print(arr[rx][ry]);
	}
}
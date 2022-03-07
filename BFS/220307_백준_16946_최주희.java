import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		int num = 1;
		int[][] group = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		int[] result = new int[1000001];

		//그룹 표시
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 0 && group[i][j] == 0) {
					int cnt = 1;
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[]{i,j});
					visited[i][j] = true;
					group[i][j] = num;

					while(!q.isEmpty()) {
						int[] x = q.poll();

						for(int k=0; k<4; k++) {
							int nx = x[0] + dx[k];
							int ny = x[1] + dy[k];
							if(nx<0 || nx>=n || ny<0 || ny>=m || visited[nx][ny] || map[nx][ny] > 0) continue;
							cnt++;
							visited[nx][ny] = true;
							group[nx][ny] = num;
							q.offer(new int[]{nx,ny});
						}
					}
					result[num] = cnt;
					num++;
				}
			}
		}

		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				int sum = 0;
				if(map[i][j] == 1) {
					sum ++;
					boolean[] checked = new boolean[num];

					for(int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						if(nx<0 || nx>=n || ny<0 || ny>=m || map[nx][ny] > 0) continue;
						if(checked[group[nx][ny]]) continue;  //방문한 그룹이면 생략
						checked[group[nx][ny]] = true;
						sum += result[group[nx][ny]];  //그룹의 원소개수를 더해준다
					}
				}
				sb.append(sum%10);
			}
			sb.append('\n');
		}
		br.close();
		System.out.print(sb);
	}
}
import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] arr = new char[r][c];
		List<Integer> list = new ArrayList<>();
		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < r; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
			 	if(arr[i][j] == 'X')
					 list.add(i*c+j);
			}
		}

		int minX = r, maxX = 0, minY = c, maxY = 0;
		int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
		for (Integer pos : list) {
			int x = pos / c;
			int y = pos % c;
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				//지도 범위를 벗어나는 곳은 모두 바다
				if(nx<0 || nx>=r || ny<0 || ny>=c || arr[nx][ny] == '.') cnt++;
			}

			if(cnt < 3) {
				minX = Math.min(minX, x);
				maxX = Math.max(maxX, x);
				minY = Math.min(minY, y);
				maxY = Math.max(maxY, y);
			} else {
				q.offer(pos);
			}
		}
		while(!q.isEmpty()) {
			int x = q.peek() / c;
			int y = q.peek() % c;
			q.poll();
			arr[x][y] = '.';
		}

		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		br.close();
		System.out.print(sb);
	}
}
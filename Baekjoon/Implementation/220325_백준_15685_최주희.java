import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[][] visited = new boolean[101][101];
		int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			List<int[]> list = new ArrayList<>(); //x,y,방향
			//0세대 넣어주기
			list.add(new int[] {x, y, d});  //시작점
			list.add(new int[] {x + dx[d], y + dy[d], d});  //d방향으로 움직인 점
			visited[x][y] = true;
			visited[x + dx[d]][y + dy[d]] = true;
			int nx = x+dx[d], ny = y + dy[d];  //끝점

			for (int j = 1; j <= g; j++) {  //g세대까지 반복
				for (int k = list.size() - 1; k > 0; k--) {  //가장 끝점 부터 앞으로 가기
					int ndir = (list.get(k)[2] + 1) % 4;  //해당 점의 방향을 반시계방향으로 돌리기
					nx += dx[ndir];
					ny += dy[ndir];
					if(nx<0 || nx>100 || ny<0 || ny>100) continue;
					list.add(new int[]{nx,ny,ndir});
					visited[nx][ny] = true;
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i <= 99; i++) {
			for (int j = 0; j <= 99; j++) {
				//네개점이 true일 경우 정사각형임
				if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) {
					cnt++;
				}
			}
		}
		br.close();
		System.out.print(cnt);
	}
}
import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		char[][] map = new char[w][h];
		int[][] visited = new int[w][h];
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < w; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < h; j++) {
				if(q.isEmpty() && map[i][j] == 'C') {
					q.offer(new int[]{i,j,-1,0});  //x,y,이전방향,거울 개수
					visited[i][j] = 1;
				}
			}
		}

		int result = Integer.MAX_VALUE;
		int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};  //하우상좌
		while(!q.isEmpty()) {
			int[] x = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = x[0] + dx[i];
				int ny = x[1] + dy[i];
				int mirror = x[3];

				if(nx<0 || nx>=w || ny<0 || ny>=h || map[nx][ny] == '*') continue;
				if(x[2] >=0 && x[2]!=i) mirror++; //방향회전 : 이전 방향과 다르다
				if(visited[nx][ny] == 0 && map[nx][ny] == 'C') {
					result = Math.min(result, mirror);
					continue;
				}
				if(visited[nx][ny] > 0) { //이미 갔던 곳이라면 거울 개수가 더 작으면 넣어줌
					if(visited[nx][ny] >= mirror){
						visited[nx][ny] = mirror;
						q.offer(new int[]{nx,ny,i,mirror});
					}
				}
				else {
					visited[nx][ny] = mirror;
					q.offer(new int[]{nx,ny,i,mirror});
				}
			}
		}
		br.close();
		System.out.print(result==Integer.MAX_VALUE?0:result);
	}
}
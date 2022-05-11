import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(br.readLine());
		br.close();

		if(c*r<k) {  //좌석을 배정할 수 없는 경우 0 출력
			System.out.print(0);
			return;
		}
		int x = r-1, y = 0, dir = 0;
		boolean[][] visited = new boolean[r][c];
		int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

		for(int i=1; i<k; i++){
			visited[x][y] = true;
			//가려던 방향에 빈 좌석이 없으면 방향 전환
			if((x+dx[dir])<0 || (x+dx[dir])>=r || (y+dy[dir])<0 || (y+dy[dir])>=c || visited[x+dx[dir]][y+dy[dir]])
				dir = (dir+1)%4;
			x += dx[dir];
			y += dy[dir];
		}
		System.out.print((y+1) + " " + (r-x));
	}
}
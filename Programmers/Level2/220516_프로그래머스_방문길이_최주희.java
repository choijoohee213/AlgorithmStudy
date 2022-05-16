class Solution {
	public int solution(String dirs) {
		int answer = 0, x = 5, y = 5;
		int[] dx = {-1,1,0,0}, dy = {0,0,1,-1};
		boolean[][][][] visited = new boolean[11][11][11][11];

		for (int i = 0; i < dirs.length(); i++) {
			char cmd = dirs.charAt(i);

			int dir = 0;
			if(cmd == 'U') dir = 0;
			else if(cmd == 'D') dir = 1;
			else if(cmd == 'R') dir = 2;
			else if(cmd == 'L') dir = 3;

			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if(nx<0 || nx>=11 || ny<0 || ny>=11) continue;
			if(!visited[x][y][nx][ny] && !visited[nx][ny][x][y]) answer++;
			visited[x][y][nx][ny] = true;
			visited[nx][ny][x][y] = true;
			x = nx;
			y = ny;
		}
		return answer;
	}
}
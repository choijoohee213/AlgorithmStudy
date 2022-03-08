import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[][] arr = new char[8][8];
		int[] dx = {0,1,0,-1,0,1,-1,-1,1}, dy = {0,0,1,0,-1,1,1,-1,-1};
		for(int i=0; i<8; i++){
			arr[i] = br.readLine().toCharArray();
		}
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{7,0});
		int cnt = q.size();

		while(!q.isEmpty()) {
			int[] x = q.poll();
			cnt--;
			if(arr[x[0]][x[1]] == '#') continue;  //벽이 캐릭터가 있는칸으로 이동하면 생략

			for(int i=0; i<9; i++) {
				int nx = x[0] + dx[i];
				int ny = x[1] + dy[i];

				if(nx<0 || nx>=8 || ny<0 || ny>=8 || arr[nx][ny] == '#') continue;
				if(nx == 0 && ny == 7) {
					System.out.print(1);
					return;
				}
				q.offer(new int[]{nx,ny});
			}

			//넣은 큐사이즈 만큼 돌고 벽이동
			if(cnt>0) continue;
			cnt = q.size();
			for(int i=7; i>=0; i--) {
				for(int j=7; j>=0; j--) {
					if(i-1 < 0) arr[i][j] = '.';
					else arr[i][j] = arr[i-1][j];
				}
			}
		}
		br.close();
		System.out.print(0);
	}
}
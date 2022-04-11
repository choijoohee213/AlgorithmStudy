import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			char[][] arr = new char[h+2][w+2];
			boolean[][] visited = new boolean[h+2][w+2];
			int result = 0;

			for (int i = 1; i <= h; i++) {
				String s = br.readLine();
				for (int j = 0; j < w; j++) {
					arr[i][j+1] = s.charAt(j);
				}
			}

			int key = 0;
			String skey = br.readLine();
			if(skey.charAt(0) != '0') {
				for (int i = 0; i < skey.length(); i++) {
					key |= 1 << (skey.charAt(i)-'a');
				}
			}

			Queue<int[]> q = new ArrayDeque<>();
			Queue<int[]>[] wait = new ArrayDeque[26];
			for (int i = 0; i < 26; i++) {
				wait[i] = new ArrayDeque<>();
			}

			//가장자리 검사
			for (int i = 0; i <= w+1; i++) {
				q.offer(new int[]{0,i});
				q.offer(new int[]{h+1,i});
			}

			for (int i = 1; i <= h; i++) {
				q.offer(new int[]{i,0});
				q.offer(new int[]{i,w+1});
			}

			while(!q.isEmpty()) {
				int[] x = q.poll();
				visited[x[0]][x[1]] = true;

				for (int i = 0; i < 4; i++) {
					int nx = x[0] + dx[i];
					int ny = x[1] + dy[i];

					if(nx<1 || nx>=h+1 || ny<1 || ny>=w+1 || arr[nx][ny] == '*' || visited[nx][ny]) continue;
					if(arr[nx][ny] == '$') {  //문서
						result++;
						visited[nx][ny] = true;
						q.offer(new int[]{nx,ny});
					} else if(arr[nx][ny] >= 'a' && arr[nx][ny] <= 'z') {  //열쇠
						key |= 1 << (arr[nx][ny] - 'a');  //열쇠 추가
						visited[nx][ny] = true;
						q.offer(new int[]{nx,ny});

						while(!wait[arr[nx][ny] - 'a'].isEmpty()) {  //이전에 못열었던 문 열기
							int[] door = wait[arr[nx][ny] - 'a'].poll();
							q.offer(new int[]{door[0], door[1]});
						}
					} else if(arr[nx][ny] >= 'A' && arr[nx][ny] <= 'Z') { //문
						int check = key & (1 << (arr[nx][ny] - 'A'));  //문을 열 열쇠가 있는지 확인
						if(check == 0) {  //열쇠가 없다면
							wait[arr[nx][ny] - 'A'].offer(new int[]{nx,ny});
						} else {
							visited[nx][ny] = true;
							q.offer(new int[]{nx,ny});
						}
					} else {  //빈 공간
						visited[nx][ny] = true;
						q.offer(new int[]{nx,ny});
					}
				}
			}
			sb.append(result).append('\n');
		}
		br.close();
		System.out.print(sb);
	}
}
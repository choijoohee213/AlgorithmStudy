import java.io.*;
import java.util.*;

/**
 * 1. m명의 승객을 태워 도착지로 데려다줄때마다 연료가 충전되고, 연료가 바닥나면 업무 끝
 * 2. 최단경로로, 인접한 빈칸으로 이동(벽이 없는곳)
 * 3. 한명씩 탑승하고 데려다주고를 m번 반복
 * 		- 현재 위치에서 최단거리가 가장짧은 승객순으로 태움
 *		- 그런 승객이 여러명이면 그 중 행번호 작은 순 -> 열 번호 작은 순
 * 4. 승객을 목적지에 태워다주면 이동 중 소모한 연료의 두배가 충전됨
 * 5. 목적지에 도달하기 전 연료가 없으면 종료
 * 		- 목적지 이동시킨 동시에 연료가 바닥나는 경우는 종료 x
 *
 */

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		HashMap<Integer, Integer> customer = new HashMap<>();
		int[] dx = {-1,0,0,1}, dy = {0,-1,1,0};

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int curX = Integer.parseInt(st.nextToken()) - 1;
		int curY = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int ex = Integer.parseInt(st.nextToken()) - 1;
			int ey = Integer.parseInt(st.nextToken()) - 1;
			customer.put(sx*n+sy, ex*n+ey);
		}
		br.close();

		for (int i = 0; i < m; i++) {
			int[][] visited = new int[n][n];
			Queue<Integer> q = new ArrayDeque<>();
			Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
				if(o1[0] == o2[0]) {
					if(o1[1] == o2[1]) return o1[2] - o2[2];
					return o1[1]-o2[1];
				}
				return o1[0]-o2[0];
			});
			q.offer(curX*n+curY);
			visited[curX][curY] = 1;
			int ex = -1, ey = -1, sum = 0;
			boolean flag = false;

			while(!q.isEmpty()) {
					int x = q.peek() / n;
					int y = q.peek() % n;
					q.poll();

					if(customer.containsKey(x*n+y)) {  //승객이 있는 곳이라면
						//우선순위큐에 승객 저장
						pq.offer(new int[]{visited[x][y]-1, x, y});
					}

					for (int j = 0; j < 4; j++) {
						int nx = x + dx[j];
						int ny = y + dy[j];

						if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny] > 0 || arr[nx][ny] == 1) continue;
						visited[nx][ny] = visited[x][y] + 1;
						q.offer(nx*n+ny);
					}

			}
			if(!pq.isEmpty()) {
				int[] x = pq.poll();
				fuel -= x[0];  //움직인만큼 연료 소모
				curX = x[1];  //택시 위치 바꿔주기
				curY = x[2];
				ex = customer.get(curX*n + curY) / n;  //도착지 정보 저장
				ey = customer.get(curX*n + curY) % n;
				customer.remove(curX * n + curY);  //승객 리스트에서 태운 승객 제거
			}
			if(fuel <= 0 || ex == -1) {  //연료가 없거나 승객을 만나지 못했으면 종료
				System.out.print(-1);
				return;
			}

			//손님을 목적지까지 태워다주기
			visited = new int[n][n];
			q = new ArrayDeque<>();
			q.offer(curX*n+curY);
			visited[curX][curY] = 1;

			while(!q.isEmpty()) {
				int x = q.peek() / n;
				int y = q.peek() % n;
				q.poll();

				if(x == ex && y == ey) {  //도착지라면
					curX = x;  //다음위치로 택시 이동
					curY = y;
					sum += visited[x][y]-1;  //오는 동안 소모한 연료 저장
					fuel -= visited[x][y]-1;  //움직인만큼 연료 소모
					flag = true;
					break;
				}

				for (int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny] > 0 || arr[nx][ny] == 1) continue;
					visited[nx][ny] = visited[x][y] + 1;
					q.offer(nx*n+ny);
				}
			}
			if(fuel < 0 || !flag) {  //연료가 다 떨어졌거나 승객을 데려다주지 못했다면 종료
				System.out.print(-1);
				return;
			}
			fuel += 2 * sum;  //움직인 수 * 2 만큼 연료 충전
		}
		System.out.print(customer.size()==0? fuel : -1);
	}
}
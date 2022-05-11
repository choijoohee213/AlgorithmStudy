import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean[][][] visited = new boolean[n][n][n*n+1];
		boolean[][] turnOn = new boolean[n][n];
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
		Map<Integer, List<Integer>> switchMap = new HashMap<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			if(!switchMap.containsKey(x*n+y)) {
				switchMap.put(x*n+y, new ArrayList<>());
			}
			switchMap.get(x*n+y).add(a*n+b);
		}

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(0);
		turnOn[0][0] = true;
		Set<Integer> switchOn = new HashSet<>(); //불이 켜진 방
		switchOn.add(0);
		visited[0][0][switchOn.size()] = true;

		while(!q.isEmpty()) {
			int x = q.peek() / n;
			int y = q.peek() % n;
			q.poll();

			if(switchMap.containsKey(x*n+y)){
				List<Integer> list = switchMap.get(x*n+y);
				for (Integer room : list) {
					turnOn[room/n][room%n] = true;
					switchOn.add(room);
				}
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				//새로 불이 켜진방이 있다면 왔던곳도 다시갈 수 있게해야함 -> visited 3차원배열
				if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny][switchOn.size()] || !turnOn[nx][ny]) continue;
				visited[nx][ny][switchOn.size()] = true;
				q.offer(nx*n+ny);
			}
		}

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(turnOn[i][j]) {
					cnt++;
				}
			}
		}
		br.close();
		System.out.print(cnt);
	}
}
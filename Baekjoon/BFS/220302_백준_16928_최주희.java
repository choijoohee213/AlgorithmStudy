import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] ladder = new int[101];
		int[] snake = new int[101];
		boolean[] visited = new boolean[101];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ladder[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			snake[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		br.close();

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{1, 0});  //칸 번호, 주사위 굴린 횟수
		visited[1] = true;

		while(!q.isEmpty()) {
			int[] x = q.poll();

			for(int i=1; i<=6; i++) {
				int nx = x[0] + i;
				if(nx<100 && visited[nx]) continue;  //방문했으면 생략
				while(nx<100 && (ladder[nx]!=0 || snake[nx]!=0)) {  //사다리와 뱀 여부 따라 이동
					visited[nx] = true;
					if(ladder[nx] != 0) nx = ladder[nx];
					else nx = snake[nx];
				}
				if(nx > 100) continue;
				if(nx == 100) {  //도착
					System.out.print(x[1]+1);
					return;
				}
				q.offer(new int[]{nx, x[1]+1});
				visited[nx] = true;
			}
		}
	}
}
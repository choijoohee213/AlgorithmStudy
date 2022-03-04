import java.io.*;
import java.util.*;

class Main {
	static boolean[][] visited = new boolean[1501][1501];
	static Queue<int[]> q = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int[] arr = new int[3];
		arr[0] = Integer.parseInt(st.nextToken());
		arr[1] = Integer.parseInt(st.nextToken());
		arr[2] = Integer.parseInt(st.nextToken());
		if(arr[0] == arr[1] && arr[1] == arr[2]) {
			System.out.println("1");
			return;
		}
		if((arr[0]+arr[1]+arr[2]) % 3 != 0) {  //3으로 나누어지지 않으면 동일한 개수로 나눌 수 없음
			System.out.print("0");
			return;
		}
		compare(arr[0], arr[1], arr[2]);
		compare(arr[0], arr[2], arr[1]);
		compare(arr[1], arr[2], arr[0]);

		while(!q.isEmpty()) {
			int[] d = q.poll();
			if(d[0] == d[1] && d[1] == d[2]) {
				System.out.print("1");
				return;
			}
			compare(d[0], d[1], d[2]);
			compare(d[1], d[2], d[0]);
			compare(d[0], d[2], d[1]);
		}
		System.out.print("0");
		br.close();
	}

	static void compare(int a, int b, int c) {
		int x = Math.min(a,b);
		int y = Math.max(a,b);

		if(visited[x*2][y-x] || visited[y-x][x*2]) return;
		visited[x*2][y-x] = visited[y-x][x*2] = true;
		q.offer(new int[]{x*2, y-x, c});
	}
}
import java.io.*;
import java.util.*;

class Node {
	int idx, limit;

	public Node(int idx, int limit) {
		this.idx = idx;
		this.limit = limit;
	}
}

class Main {
	static List<Node>[] list;
	static boolean flag = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int min = 0, max = 0;

		list = new List[n+1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
			max = Math.max(max, c);
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		while(min <= max) {
			int mid = (min + max) / 2;
			flag = false;
			dfs(start, end, mid, new boolean[n+1]);
			if(flag) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		br.close();
		System.out.print(max);
	}

	private static void dfs(int num, int end, int limit, boolean[] visited) {
		if(flag || num == end) {
			flag = true;
			return;
		}

		for (Node node : list[num]) {
			if(visited[node.idx] || node.limit < limit) continue;
			visited[node.idx] = true;
			dfs(node.idx, end, limit, visited);
		}
	}
}
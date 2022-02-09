import java.util.*;
import java.io.*;

class Main {
	static int n, result = Integer.MAX_VALUE;
	static int[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		visited = new boolean[n];

		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		split(0, 0);
		System.out.print(result);
	}

	static void split(int idx, int cnt) {
		if(cnt == n/2) {
			int sum1 = 0, sum2 = 0;
			for(int i=0; i<n; i++) {
				for(int j=i+1; j<n; j++) {
					if(visited[i] && visited[j]) sum1 += arr[i][j] + arr[j][i];
					else if(!visited[i] && !visited[j]) sum2 += arr[i][j] + arr[j][i];
				}
			}
			result = Math.min(result, Math.abs(sum1 - sum2));
			return;
		}
		for(int i=idx; i<n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			split(i+1, cnt+1);
			visited[i] = false;
		}
	}
}